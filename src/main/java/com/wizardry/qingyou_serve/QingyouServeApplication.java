package com.wizardry.qingyou_serve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wizardry.qingyou_serve.mapper")
public class QingyouServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingyouServeApplication.class, args);
    }

}
