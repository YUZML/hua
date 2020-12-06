package com.zsh.common.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:common.properties")
public class CommonConfig {

    @Value("${aaa.a}")
    private String a;
    @Value("${aaa.b}")
    private String b;
    @Value("${aaa.c}")
    private String c;

    public void show(){
        System.out.println("a --- > " + a);
        System.out.println("b --- > " + b);
        System.out.println("c --- > " + c);
    }
}
