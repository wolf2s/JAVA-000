package com.example.jdbc.primordial.impl;

import com.example.jdbc.primordial.IUserService;
import com.example.jdbc.primordial.MysqlHelper;
import com.example.jdbc.primordial.User;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService {

    @Override
    public int saveUser(Long id, String userName) {
        MysqlHelper mysqlHelper = new MysqlHelper();
        String sql = "insert user (id,name) value(1,张三)";
        mysqlHelper.executeSQL(sql);
        mysqlHelper.close();
        return 0;
    }

    @Override
    public int updateUser(Long id, String userName) {
        return 0;
    }

    @Override
    public User selectUser(Long id) {
        return null;
    }

    @Override
    public int remove(Long id) {
        return 0;
    }
}
