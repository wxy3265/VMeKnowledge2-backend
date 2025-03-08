package com.vmeknowledge.service;

// All use redis:string
public interface RedisStringService {

    void set(String key, Object value);

    void set(String key, Object value, Long expire);

    Object get(String key);

    void expire(String key, long expire);

    void delete(String key);

}
