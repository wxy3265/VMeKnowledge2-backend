package com.vmeknowledge.config;

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
}
