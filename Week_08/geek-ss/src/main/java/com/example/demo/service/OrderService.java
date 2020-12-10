package com.example.demo.service;

import com.example.demo.model.Order;

import java.util.List;

public interface OrderService {

    /**
     * 查询信息
     *
     * @return
     */
    List<Order> queryList();

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Order getById(Long id);

    /**
     * 更新
     *
     * @param id
     * @param userName
     * @return
     */
    int update(Long id, String userName);

    /**
     * 保存
     *
     * @param id
     * @param name
     * @return
     */
    int save(Long id, Long userId, String name);

}
