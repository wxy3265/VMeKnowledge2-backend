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
        String s = "登錄的用戶id為："+UserThreadLocal.getCurrentId();
        return Result.success(s);
    }
}
