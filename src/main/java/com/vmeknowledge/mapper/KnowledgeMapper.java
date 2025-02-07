package com.vmeknowledge.mapper;

import com.vmeknowledge.pojo.Knowledge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KnowledgeMapper extends MongoRepository<Knowledge,Integer> {
}
