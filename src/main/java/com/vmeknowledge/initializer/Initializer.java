package com.vmeknowledge.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Initializer {

    @Autowired
    private MysqlInitializer mysqlInitializer;

    public void init() {
        mysqlInitializer.initializeAllMappers();
    }

}
