package org.akira.mybatisjpa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.akira.mybatisjpa.repositories.mybatis")
public class MybatisjpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisjpaApplication.class, args);
    }

}
