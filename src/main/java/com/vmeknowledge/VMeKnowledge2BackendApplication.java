package com.vmeknowledge;

import com.vmeknowledge.initializer.Initializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class VMeKnowledge2BackendApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VMeKnowledge2BackendApplication.class, args);
        Initializer initializer = context.getBean(Initializer.class);
        initializer.init();
        String url, dryRun = System.getenv("DRY_RUN");
        if (!dryRun.equals("0")) {
//            url = "jdbc:mysql://mysql-test" + dryRun + ":300" + dryRun + "/vmeknowledge";
            url = "jdbc:mysql://mysql-test" + dryRun + ":3306/vmeknowledge";
        } else {
            url = "jdbc:mysql://mysql-online:3000/vmeknowledge";
        }
        try {
            Connection connection = DriverManager.getConnection(url, "root", "31321");
            log.debug("连接成功！");
        } catch (SQLException e) {
            log.error("连接失败！");
            throw new RuntimeException(e);
        }
    }

}
