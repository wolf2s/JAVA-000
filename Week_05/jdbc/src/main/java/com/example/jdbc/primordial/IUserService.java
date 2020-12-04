package com.example.jdbc.primordial;

public interface IUserService {

    /**
     * 增加用户
     *
     * @param id
     * @param userName
     * @return
     */
    int saveUser(Long id, String userName);

    /**
     * 更改用户信息
     *
     * @param id
     * @param userName
     * @return
     */
    int updateUser(Long id, String userName);


    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    User selectUser(Long id);

    /**
     * 移除客户信息
     *
     * @param id
     * @return
     */
    int remove(Long id);


}
