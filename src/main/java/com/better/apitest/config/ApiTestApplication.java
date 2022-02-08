package com.better.apitest.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("com.better")
@MapperScan("com.better.apitest.mapper")
@SpringBootApplication
public class ApiTestApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ApiTestApplication.class);
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiTestApplication.class);
        Environment environment = application.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址: \thttp://127.0.0.1:{}", environment.getProperty("server.port"));
    }
}
