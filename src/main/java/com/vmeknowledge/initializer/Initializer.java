package com.vmeknowledge.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private MysqlInitializer mysqlInitializer;

    @PostConstruct
    public void init() {
        mysqlInitializer.initializeAllMappers();
    }
}