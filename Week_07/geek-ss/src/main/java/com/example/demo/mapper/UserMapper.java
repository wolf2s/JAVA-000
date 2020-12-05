package com.example.demo.mapper;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 查询信息
     * @return
     */
    List<User> queryInfo();

    /**
     * 保存
     * @return
     */
    int saveInfo(User user);

}
