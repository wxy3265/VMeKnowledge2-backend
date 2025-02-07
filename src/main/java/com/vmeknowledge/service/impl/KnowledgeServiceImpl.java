package com.vmeknowledge.service.impl;

import com.vmeknowledge.mapper.KnowledgeMapper;
import com.vmeknowledge.pojo.Knowledge;
import com.vmeknowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Knowledge saveKnowledge(Knowledge knowledge){
        knowledge.setCreateTime(LocalDateTime.now());
        knowledge.setUpdateTime(LocalDateTime.now());
        return knowledgeMapper.save(knowledge);
    }
    public List<Knowledge> getAllKnowledge(){
        return knowledgeMapper.findAll();
    }
    public Knowledge getKnowledgeById(int id){
        return knowledgeMapper.findById(id).orElse(null);
    }
    public void deleteKnowledgeById(int id){
        knowledgeMapper.deleteById(id);
    }

    public Knowledge updateKnowledge(int id, Knowledge updatedKnowledge) {
        // 创建查询条件
        Criteria criteria = Criteria.where("id").is(id);

        // 创建更新对象
        Update update = new Update();
        update.set("title", updatedKnowledge.getTitle());
        update.set("description", updatedKnowledge.getDescribe());
        update.set("content", updatedKnowledge.getContent());
        update.set("updateTime", LocalDateTime.now());

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

        return updatedKnowledgeResult;
    }
}
