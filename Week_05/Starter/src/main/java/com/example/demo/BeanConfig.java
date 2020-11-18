package com.example.demo;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    public Student student100() {
        return new Student();
    }

}
