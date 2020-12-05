package com.example.demo.service;

public interface UserService {

    /**
     * 查询
     */
    void query();

    /**
     * 保存
     *
     * @param id
     * @param name
     * @return
     */
    int save(Integer id, String name);

}
