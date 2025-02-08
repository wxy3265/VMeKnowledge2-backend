package com.vmeknowledge.controller;

import com.vmeknowledge.common.Result;
import com.vmeknowledge.threadLocal.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ces {

    @GetMapping("/tt")
    public Result tt(){
        return Result.success(UserThreadLocal.getCurrentId());
    }
}
