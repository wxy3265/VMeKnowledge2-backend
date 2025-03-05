package com.vmeknowledge.service;

import com.vmeknowledge.pojo.Knowledge;

import java.io.IOException;
import java.util.List;

public interface KnowledgeService {
    Knowledge saveKnowledge(Knowledge knowledge);

    List<Knowledge> getUserAllKnowledge();

    Knowledge getKnowledgeById(String id);

    void deleteKnowledgeById(String id);

    Knowledge updateKnowledge(String id, Knowledge updatedKnowledge);

    Knowledge updateVisibility(String id, int visibility);

    public List<Knowledge> searchKnowledge(String keyword, int page, int size) throws IOException;

}
