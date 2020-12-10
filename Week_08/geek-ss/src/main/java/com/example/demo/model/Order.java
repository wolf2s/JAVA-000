package com.example.demo.model;

import lombok.Data;

@Data
public class Order {

    /**
     * id
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 名称
     */
    private String userName;


}
