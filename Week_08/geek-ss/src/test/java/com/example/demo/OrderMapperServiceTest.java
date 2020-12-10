package com.example.demo;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class OrderMapperServiceTest {


    @Resource
    private OrderService testService;

    /**
     * 保存数据
     */
    @Test
    public void testSave() {
        for (int i = 0; i <= 100000; i++) {
            int userId = (int) (Math.random() * 10000);
            testService.save(Long.valueOf(i), Long.valueOf(userId), i + "");
        }
    }


    /**
     * 查询数据
     */
    @Test
    public void testQuery() {
        List<Order> orders = testService.queryList();
        System.out.println(orders);
    }

    /**
     * 更新数据
     */
    @Test
    public void testGetById(){
        Order order = testService.getById(2456L);
        System.out.println(order);
    }

    @Test
    public void testUpdate(){
        Long id = 2458L;
        testService.update(id,id.toString());
        Order order = testService.getById(2456L);
        System.out.println(order);
    }


}
