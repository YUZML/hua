package com.zsh.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CommonApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CommonApplication.class, args);
    }

}
