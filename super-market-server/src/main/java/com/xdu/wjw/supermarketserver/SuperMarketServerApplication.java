package com.xdu.wjw.supermarketserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.xdu.wjw.supermarketmodel.model.mapper")
public class SuperMarketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperMarketServerApplication.class, args);
    }

}
