package com.example;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource(value = { "classpath:application.properties" })
@EnableAutoConfiguration
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(env.getRequiredProperty("spring.datasource.url"))
                .username(env.getRequiredProperty("spring.datasource.username"))
                .password(env.getRequiredProperty("spring.datasource.password"))
                .driverClassName(env.getRequiredProperty("spring.datasource.driverClassName"))
                .build();
    }

//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource dataSource() {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
//        ds.setUsername(env.getRequiredProperty("spring.datasource.username"));
//        ds.setPassword(env.getRequiredProperty("spring.datasource.password"));
//        ds.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
//
//        return ds;
//    }

}


