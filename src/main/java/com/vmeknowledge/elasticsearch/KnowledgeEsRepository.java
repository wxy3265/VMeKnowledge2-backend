package com.vmeknowledge.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface KnowledgeEsRepository extends ElasticsearchRepository<KnowledgeEs, String> {
}