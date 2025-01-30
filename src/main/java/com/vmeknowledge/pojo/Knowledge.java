package com.vmeknowledge.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "knowledge")
public class Knowledge {
    @Id
    private int id;
    private String title;
    private String describe;
    private LocalDateTime createTime;
    private String content;
}