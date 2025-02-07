package com.vmeknowledge.controller;

import com.vmeknowledge.common.Result;
import com.vmeknowledge.pojo.Knowledge;
import com.vmeknowledge.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    @PostMapping
    public Result saveKnowledge(@RequestBody Knowledge knowledge){
        log.info("新增知识：{}",knowledge);
        Knowledge savedKnowledge = knowledgeService.saveKnowledge(knowledge);
        return Result.success(savedKnowledge);
    }

    @GetMapping
    public Result getAllKnowledge(){
        log.info("查询所有知识");
        List<Knowledge> knowledgeList = knowledgeService.getAllKnowledge();
        return Result.success(knowledgeList);
    }

    @GetMapping("/{id}")
    public Result getKnowledgeById(@PathVariable Object id){
        log.info("根据id获取知识");
        Knowledge knowledge = knowledgeService.getKnowledgeById(id);
        return Result.success(knowledge);
    }

    @DeleteMapping("/{id}")
    public Result deleteKnowledgeById(@PathVariable Object id){
        log.info("根据id删除知识");
        knowledgeService.deleteKnowledgeById(id);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result updateKnowledge(@PathVariable Object id, @RequestBody Knowledge updatedKnowledge) {
        log.info("更新知识：{}", updatedKnowledge);
        Knowledge updatedKnowledgeResult = knowledgeService.updateKnowledge(id, updatedKnowledge);
        return Result.success(updatedKnowledgeResult);
    }
}
