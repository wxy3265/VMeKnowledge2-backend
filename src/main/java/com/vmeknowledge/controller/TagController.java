package com.vmeknowledge.controller;

import com.vmeknowledge.client.RedisClient;
import com.vmeknowledge.common.Result;
import com.vmeknowledge.service.KnowledgeService;
import com.vmeknowledge.threadLocal.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {

    @Autowired
    private RedisClient redisClient;

    @GetMapping()
    public Result getAllTag() {
        String key="user:"+ UserThreadLocal.getCurrentId()+":tag"+":usage";
        Map<String, Integer> tag = (Map<String, Integer>) redisClient.getHashMap(key);
        return Result.success(tag.keySet());
    }
    @PostMapping()
    public Result insert(@RequestBody String tag){
        String key="user:"+ UserThreadLocal.getCurrentId()+":tag"+":usage";
        redisClient.SetHashMap(key,tag,0);
        return Result.success();
    }

    @DeleteMapping()
    public Result delete(@RequestBody String tag){
        String key="user:"+ UserThreadLocal.getCurrentId()+":tag"+":usage";
        if ((Integer)redisClient.getHashMap(key,tag)!=0){
            return Result.error("该标签仍有知识在使用，无法删除！");
        }
        redisClient.deleteHashMap(key,tag);
        return Result.success();
    }
}
