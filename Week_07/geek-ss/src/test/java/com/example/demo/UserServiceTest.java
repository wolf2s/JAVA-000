package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {

    @Test
    void contextLoads() {
    }

    @Resource
    private UserService testService;

    @Test
    public void testQuery() {
        testService.query();
    }

    @Test
    public void testSave(){
        testService.save(5,"5");
    }


}
