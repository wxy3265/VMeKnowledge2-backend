package com.vmeknowledge.mapper;

import com.vmeknowledge.pojo.Knowledge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KnowledgeMapper extends MongoRepository<Knowledge,String> {
    List<Knowledge> findAllByUserId(int userId);
}
