package com.vmeknowledge.controller;

import com.vmeknowledge.pojo.UserInformation;
import com.vmeknowledge.utils.JwtUtils;
import com.vmeknowledge.common.Result;
import com.vmeknowledge.pojo.UserAccount;
import com.vmeknowledge.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 用户登录
     * @param account
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserAccount account) {
        UserAccount e = userAccountService.login(account);

        //登陆成功，生成令牌，下发令牌
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);

        }

        //登录失败，返回错误信息
        else return Result.error("用户名或密码错误");
    }

    /**
     * 用户注册
     * @param account
     * @return Result
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserAccount account) {
        if (userAccountService.selectByUsername(account.getUsername()) != null){
            return Result.error("用户名已存在");
        }
        userAccountService.register(account);
        return Result.success();
    }

    /**
     * 完善基本信息
     * @param info
     * @return Result
     */
    @PutMapping("/user")
    public Result update(@RequestBody UserInformation info){
        log.info("修改：{}", info);
        userAccountService.update(info);
        return Result.success();
    }
}
