package com.vmeknowledge.rediscache;

import com.vmeknowledge.service.RedisStringService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    RedisStringService redisStringService;

    @Pointcut("@annotation(com.vmeknowledge.rediscache.RedisCache)")
    public void redisCachePointCut() {}

    @Around("redisCachePointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder cacheRedisKeySb = new StringBuilder("cache").append(":");
        String className = joinPoint.getTarget().toString().split("@")[0];
        cacheRedisKeySb.append(className).append(":");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        cacheRedisKeySb.append(methodName);
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                cacheRedisKeySb.append(":").append(arg);
            }
        }
        String cacheRedisKey = cacheRedisKeySb.toString();
        // get from redis cache
        Object res = redisStringService.get(cacheRedisKey);
        if (res != null) {
            log.info("Get data from redis cache by key:{} data:{}", cacheRedisKey, res);
        } else {
            // get from cache fail, get from origin
            try {
                res = joinPoint.proceed();
                log.info("Get data from redis cache by key:{}failed. Retry from original data base", cacheRedisKey);
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        RedisCache redisCache = signature.getMethod().getAnnotation(RedisCache.class);
        long expire = redisCache.expire();
        redisStringService.set(cacheRedisKey, res, expire);
        return res;
    }

}
