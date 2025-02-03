package com.vmeknowledge.service;

import com.vmeknowledge.pojo.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KnowledgeService {
    Knowledge saveKnowledge(Knowledge knowledge);

    List<Knowledge> getAllKnowledge();

    Knowledge getKnowledgeById(int id);

    void deleteKnowledgeById(int id);

    Knowledge updateKnowledge(int id, Knowledge updatedKnowledge);
}
