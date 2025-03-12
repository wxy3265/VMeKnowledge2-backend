package com.vmeknowledge;

import com.vmeknowledge.initializer.Initializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.vmeknowledge.mapper")
public class VMeKnowledge2BackendApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VMeKnowledge2BackendApplication.class, args);
        Initializer initializer = context.getBean(Initializer.class);
        initializer.init();
    }

}
