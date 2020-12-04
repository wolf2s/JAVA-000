package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void query() {
        User user = userMapper.queryInfo();
        log.info("获取了用户信息 {}", user);
    }

    @Override
    public int saveInfo(Integer id,String name) {
      User user = new User();
      user.setId(id);
      user.setName(name);
      return userMapper.saveInfo(user);
    }
}
