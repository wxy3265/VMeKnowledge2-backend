package com.vmeknowledge.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.vmeknowledge.constant.VisibilityConstant;
import com.vmeknowledge.elasticsearch.KnowledgeEs;
import com.vmeknowledge.elasticsearch.KnowledgeEsRepository;
import com.vmeknowledge.mapper.KnowledgeMapper;
import com.vmeknowledge.pojo.Knowledge;
import com.vmeknowledge.service.KnowledgeService;
import com.vmeknowledge.threadLocal.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private KnowledgeEsRepository knowledgeEsRepository;
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public Knowledge saveKnowledge(Knowledge knowledge){
        knowledge.setCreateTime(LocalDateTime.now());
        knowledge.setUpdateTime(LocalDateTime.now());
        knowledge.setUserId(UserThreadLocal.getCurrentId());
        knowledge.setVisibility(VisibilityConstant.PRIVATE);
        Knowledge savedKnowledge = knowledgeMapper.save(knowledge);
        syncToEs(savedKnowledge);
        return savedKnowledge;
    }
    public List<Knowledge> getUserAllKnowledge(){
        return knowledgeMapper.findAllByUserId(UserThreadLocal.getCurrentId());
    }
    public Knowledge getKnowledgeById(String id){
        return knowledgeMapper.findById(id).orElse(null);
    }
    public void deleteKnowledgeById(String id){
        knowledgeMapper.deleteById(id);
        knowledgeEsRepository.deleteById(id);
    }

    public Knowledge updateKnowledge(String id, Knowledge updatedKnowledge) {
        // 创建查询条件
        Criteria criteria = Criteria.where("_id").is(id); // 修改为 "_id"

        // 创建更新对象
        Update update = new Update();
        update.set("title", updatedKnowledge.getTitle());
        update.set("description", updatedKnowledge.getDescription());
        update.set("content", updatedKnowledge.getContent());
        update.set("updateTime", LocalDateTime.now());
        update.set("userId",UserThreadLocal.getCurrentId());
        update.set("visibility", updatedKnowledge.getVisibility());

        // 执行更新操作
        Knowledge updatedKnowledgeResult = mongoTemplate.findAndModify(
                new Query(criteria),
                update,
                FindAndModifyOptions.options().returnNew(true),
                Knowledge.class
        );

        if (updatedKnowledgeResult == null) {
            throw new IllegalArgumentException("Knowledge not found with ID: " + id);
        }
        syncToEs(updatedKnowledgeResult);
        return updatedKnowledgeResult;
    }

    @Override
    public Knowledge updateVisibility(String id, int visibility) {
        Criteria criteria = Criteria.where("_id").is(id);

        Update update = new Update();
        update.set("visibility", visibility);

        Knowledge updatedKnowledgeResult = mongoTemplate.findAndModify(
                new Query(criteria),
                update,
                FindAndModifyOptions.options().returnNew(true),
                Knowledge.class
        );

        if (updatedKnowledgeResult == null) {
            throw new IllegalArgumentException("Knowledge not found with ID: " + id);
        }
        syncToEs(updatedKnowledgeResult);
        return updatedKnowledgeResult;
    }

    /**
     * 同步数据到es查询类中
     * @param knowledge
     */
    private void syncToEs(Knowledge knowledge) {
        KnowledgeEs knowledgeEs = new KnowledgeEs();
        BeanUtils.copyProperties(knowledge, knowledgeEs);
        knowledgeEsRepository.save(knowledgeEs);
    }

    @Override
    public List<Knowledge> searchKnowledge(String keyword, int page, int size) throws IOException {
        int currentUserId = UserThreadLocal.getCurrentId();
        // 构建查询条件
        BoolQuery query = BoolQuery.of(b -> b
                .should(s -> s.match(MatchQuery.of(m -> m.field("title").query(keyword))))
                .should(s -> s.match(MatchQuery.of(m -> m.field("description").query(keyword))))
                .should(s -> s.match(MatchQuery.of(m -> m.field("content").query(keyword))))
                .filter(f -> f.bool(b2 -> b2
                        .should(s2 -> s2.term(TermQuery.of(t -> t.field("userId").value(FieldValue.of(currentUserId)))))
                        .should(s2 -> s2.term(TermQuery.of(t -> t.field("visibility").value(FieldValue.of(VisibilityConstant.PUBLIC)))))))
        );

        // 构建搜索请求
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("your_index_name") // 替换为实际的索引名
                .query(query._toQuery())
                .from(page * size)
                .size(size)
        );

        // 执行搜索请求
        SearchResponse<KnowledgeEs> searchResponse = elasticsearchClient.search(searchRequest, KnowledgeEs.class);

        // 将查询结果转换为 Knowledge 对象列表
        List<Knowledge> knowledgeList = new ArrayList<>();
        searchResponse.hits().hits().forEach(hit -> {
            KnowledgeEs knowledgeEs = hit.source();
            Knowledge knowledge = new Knowledge();
            BeanUtils.copyProperties(knowledgeEs, knowledge);
            knowledgeList.add(knowledge);
        });

        return knowledgeList;
    }

}