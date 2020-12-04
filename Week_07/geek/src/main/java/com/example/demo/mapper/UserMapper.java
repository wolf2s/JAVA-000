package com.example.demo.mapper;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 查询信息
     *
     * @return
     */
    User queryInfo();

    /**
     * 保存信息
     *
     * @param user
     * @return
     */
    int saveInfo(User user);


}
