package com.example.demo;


import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class UserServiceTest {

    @Resource
    private UserService testService;

    @Test
    public void testQuery() {
        testService.query();
    }

    @Test
    public void testSave(){
        testService.saveInfo(2,"2");
    }

}
