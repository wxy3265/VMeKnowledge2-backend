package com.vmeknowledge.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class ReadDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("31321");
        String dryRun = System.getenv("DRY_RUN");
        if (dryRun == null) {
            log.error("DRY_RUN environment variable not set");
            dryRun = "0";
        }
        String url = "jdbc:mysql://mysql-online:3306/vmeknowledge";
        if (!dryRun.equals("0")) {
            url += "_test_" + dryRun;
        }
        log.info("[MySQL] Running at :{}", url);
        dataSource.setUrl(url);
        return dataSource;
    }

}
