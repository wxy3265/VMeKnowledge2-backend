package com.vmeknowledge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ReadDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("31321");
        String dryRun = System.getenv("DRY_RUN");
        if (!dryRun.equals("0")) {
            dataSource.setUrl("jdbc:mysql://127.0.0.1:300" + dryRun + "/vmeknowledge");
        } else {
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3000/vmeknowledge");
        }
        return dataSource;
    }

}
