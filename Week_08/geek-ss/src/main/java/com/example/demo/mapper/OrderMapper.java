package com.example.demo.mapper;

import com.example.demo.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    /**
     * 历史查询
     *
     * @return
     */
    List<Order> queryList();

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    Order getById(Long id);

    /**
     * 保存
     *
     * @return
     */
    int saveInfo(Order order);

    /**
     * 更新
     *
     * @param order
     * @return
     */
    int updateInfo(Order order);


}
