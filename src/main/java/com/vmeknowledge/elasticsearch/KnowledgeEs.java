package com.vmeknowledge.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "knowledge")
public class KnowledgeEs {
    @Id
    private String id;
    private int userId;
    private String title;
    private String description;
    private String content;
    private int visibility;
    private String[] tags;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
