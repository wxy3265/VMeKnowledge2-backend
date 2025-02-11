package com.vmeknowledge.service;

import com.vmeknowledge.pojo.Knowledge;

import java.util.List;

public interface KnowledgeService {
    Knowledge saveKnowledge(Knowledge knowledge);

    List<Knowledge> getUserAllKnowledge();

    Knowledge getKnowledgeById(String id);

    void deleteKnowledgeById(String id);

    Knowledge updateKnowledge(String id, Knowledge updatedKnowledge);
}
