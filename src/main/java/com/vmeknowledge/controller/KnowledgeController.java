package com.vmeknowledge.controller;

import com.vmeknowledge.common.Result;
import com.vmeknowledge.pojo.Knowledge;
import com.vmeknowledge.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        log.info("查询当前用户所有知识");
        List<Knowledge> knowledgeList = knowledgeService.getUserAllKnowledge();
        return Result.success(knowledgeList);
    }

    @GetMapping("/{id}")
    public Result getKnowledgeById(@PathVariable String id){
        log.info("根据id获取知识");
        Knowledge knowledge = knowledgeService.getKnowledgeById(id);
        return Result.success(knowledge);
    }

    @DeleteMapping("/{id}")
    public Result deleteKnowledgeById(@PathVariable String id){
        log.info("根据id删除知识");
        knowledgeService.deleteKnowledgeById(id);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result updateKnowledge(@PathVariable String id, @RequestBody Knowledge updatedKnowledge) {
        log.info("更新知识：{}", updatedKnowledge);
        Knowledge updatedKnowledgeResult = knowledgeService.updateKnowledge(id, updatedKnowledge);
        return Result.success(updatedKnowledgeResult);
    }

    @PutMapping("/visibility/{id}")
    public Result updateVisibility(@PathVariable String id,@RequestBody int visibility) {
        log.info("更新可见性：{}", visibility);
        Knowledge updateVisibility = knowledgeService.updateVisibility(id, visibility);
        return Result.success(updateVisibility);
    }

    @GetMapping("/search")
    public Result searchKnowledge(
            @RequestParam @NotBlank(message = "关键词不能为空") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            log.info("搜索知识，关键词：{}，页码：{}，每页大小：{}", keyword, page, size);
            List<Knowledge> result = knowledgeService.searchKnowledge(keyword, page, size);
            return Result.success(result);
        } catch (IOException e) {
            log.error("搜索异常：{}", e.getMessage());
            return Result.error("搜索失败：" + e.getMessage());
        }
    }

}
