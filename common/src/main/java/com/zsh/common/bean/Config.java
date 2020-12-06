package com.zsh.common.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {



    @Value("${string.port}")     private int intPort;
    @Value("${string.port}")     private  String stringPort;
    @Value("${db.link.url}")     private String dbUrl;
    @Value("${db.link.driver}")  private String dbDriver;
    @Value("${db.link.username}")private String dbUsername;
    @Value("${db.link.password}")private String dbPassword;

    public void show(){
        System.out.println("===========================================");
        System.out.println("intPort :   " + (intPort + 1111));
        System.out.println("stringPort :   " + (stringPort + 1111));
        System.out.println("string :   " + dbUrl);
        System.out.println("string :   " + dbDriver);
        System.out.println("string :   " + dbUsername);
        System.out.println("string :   " + dbPassword);
        System.out.println("===========================================");
    }
}
