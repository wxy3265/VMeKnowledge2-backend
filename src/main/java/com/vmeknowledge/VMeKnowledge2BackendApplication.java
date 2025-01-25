package com.vmeknowledge;

import com.vmeknowledge.initializer.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VMeKnowledge2BackendApplication {

    public static void main(String[] args) {
        new Initializer().init();
        SpringApplication.run(VMeKnowledge2BackendApplication.class, args);
    }

}
