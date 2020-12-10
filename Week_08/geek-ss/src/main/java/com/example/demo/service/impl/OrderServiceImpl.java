package com.example.demo.service.impl;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> queryList() {
        return orderMapper.queryList();
    }

    @Override
    public Order getById(Long id) {
        return orderMapper.getById(id);
    }

    @Override
    public int update(Long id, String userName) {
        Order order = new Order();
        order.setId(id);
        order.setUserName(userName);
        return orderMapper.updateInfo(order);
    }

    @Override
    public int save(Long id, Long userId, String name) {
        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order.setUserName(name);
        return orderMapper.saveInfo(order);
    }
}
