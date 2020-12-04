package com.example.demo.service;

public interface UserService {

    /**
     * 查询
     */
     void query();

    /**
     * 保存
     */
    int saveInfo(Integer id,String name);
}
