package com.xdu.wjw.supermarketmodel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.xdu.wjw.supermarketmodel.model.mapper")
public class SuperMarketModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperMarketModelApplication.class, args);
    }

}
