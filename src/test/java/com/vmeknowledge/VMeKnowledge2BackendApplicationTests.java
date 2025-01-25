package com.vmeknowledge;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class VMeKnowledge2BackendApplicationTests {

    /**
     *  测试JWT令牌生成
     */
//    @Test
//    void testGenJWT() {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id",1);
//        claims.put("name","wxy3265");
//
//
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256,"Vmeknowledge")//签名算法
//                .setClaims(claims)//自定义内容
//                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))//有效期为一个小时
//                .compact();
//        System.out.println(jwt);
//    }

    /**
     *  测试JWT令牌解析
     */
//    @Test
//    void testParseJwt(){
//        Claims claims = Jwts.parser()
//                .setSigningKey("Vmeknowledge")//指定签名秘钥
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoid3h5MzI2NSIsImlkIjoxLCJleHAiOjE3Mzc2MDE1ODR9.29Bc6zkw9YTjpjord7mWGCI6QTnxv_DdSiq7kWVCvq0")
//                .getBody();
//        System.out.println(claims);
//    }

}
