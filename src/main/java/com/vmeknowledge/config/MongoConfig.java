package com.vmeknowledge.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.Optional;

@Configuration
@EnableMongoAuditing // 启用 Auditing 功能
public class MongoConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // 返回当前用户的标识（例如用户名），如果没有用户则返回 null
        return Optional.of("system");
    }
//    @Bean
//    public MongoClient mongoClient() {
//        String uri = System.getenv("mongodb://admin:8848@182.92.121.103:3100/Vmeknowledge");
//        if (uri == null) {
//            String host = getEnvOrThrow("182.92.121.103");
//            int port = Integer.parseInt(getEnvOrThrow("3100"));
//            String database = getEnvOrThrow("Vmeknowledge");
//            String username = getEnvOrThrow("admin");
//            String password = getEnvOrThrow("8848");
//            uri = String.format("mongodb://%s:%s@%s:%d/%s",
//                    username, password, host, port, database);
//        }
//        return MongoClients.create(uri);
//    }
//
//    private String getEnvOrThrow(String name) {
//        String value = System.getenv(name);
//        if (value == null || value.isEmpty()) {
//            throw new IllegalStateException("环境变量 " + name + " 未设置！");
//        }
//        return value;
//    }
}
