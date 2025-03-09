package com.vmeknowledge.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
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

    @Bean
    public MongoClient mongoClient() {
        String url = "mongodb://root:31321@mongo_db:27017/Vmeknowledge?authSource=admin&authMechanism=SCRAM-SHA-256";
        ConnectionString cs = new ConnectionString(url);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(cs)
                .build();
        return MongoClients.create(settings);
    }

}
