package com.vmeknowledge.client;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RedisClient {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 向 Redis 存 HashMap 指定字段数据的原始操作
     * @param key Redis key
     * @param field Hashmap field
     * @param value Hashmap value
     */
    public void SetHashMap(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 使用 Redis HashMap 类型存 Map 类型的数据
     * @param key Redis key
     * @param hashMap Map数据
     */
    public void SetHashMap(String key, Map<?,?> hashMap) {
        redisTemplate.opsForHash().putAll(key, hashMap);
    }

    /**
     * 根据 key, field 获取 Redis Hashmap 字段
     * @param key HashMap key
     * @param field HashMap field
     * @return Map[field]
     */
    public Object getHashMap(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 根据 key 获取 Redis Hashmap
     * @param key Hashmap key
     * @return Map
     */
    public Map<?, ?> getHashMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 根据 key, field 删除 Redis Hashmap 数据
     * @param key Hashmap key
     * @param field Hashmap field
     * @return 删除的字段数量
     */
    public Long deleteHashMap(String key, String field) {
        return redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 根据 key 删除 Hashmap
     * @param key Hashmap key
     * @return 删除的字段数量
     */
    public Long deleteHashMap(String key) {
        return redisTemplate.opsForHash().delete(key);
    }

}
