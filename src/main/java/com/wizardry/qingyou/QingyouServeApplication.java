package com.wizardry.qingyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 将我们原先的引导类扫描放在这里

@SpringBootApplication
public class QingyouServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingyouServeApplication.class, args);
    }

}
