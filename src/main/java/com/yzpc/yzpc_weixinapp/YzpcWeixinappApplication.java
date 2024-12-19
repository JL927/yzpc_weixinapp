package com.yzpc.yzpc_weixinapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yzpc.yzpc_weixinapp.mapper")
public class YzpcWeixinappApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzpcWeixinappApplication.class, args);
    }

}
