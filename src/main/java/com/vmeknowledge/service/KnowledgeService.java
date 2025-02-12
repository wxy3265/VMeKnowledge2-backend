package com.vmeknowledge.service;

import com.vmeknowledge.pojo.Knowledge;

import java.util.List;

public interface KnowledgeService {
    Knowledge saveKnowledge(Knowledge knowledge);

    List<Knowledge> getUserAllKnowledge();

    Knowledge getKnowledgeById(Object id);

    void deleteKnowledgeById(Object id);

    Knowledge updateKnowledge(Object id, Knowledge updatedKnowledge);

    Knowledge updateVisibility(Object id, int visibility);
}
