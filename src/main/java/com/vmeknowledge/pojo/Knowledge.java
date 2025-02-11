package com.vmeknowledge.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "knowledge")
public class Knowledge {
    @Id
    private Object id; // 添加 _id 字段
    private int userId;
    private String title;
    private String description;
    private String content;

    @CreatedDate // 自动设置为创建时间
    private LocalDateTime createTime;

    @LastModifiedDate // 自动设置为更新时间
    private LocalDateTime updateTime;
}