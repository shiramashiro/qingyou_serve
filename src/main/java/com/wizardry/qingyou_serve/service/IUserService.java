package com.wizardry.qingyou_serve.service;


import com.wizardry.qingyou_serve.entity.User;

/* 用户模块业务层接口 */
public interface IUserService {

    /**
     * 用户注册方法
     *
     * @param user 用户这个对象的数据
     */
    void reg(User user);

    /**
     * @param username 用户名
     * @param password 用户密码
     * @return 返回的是一个用户对象，如果没有查询到用户则返回null
     */
    User login(String username, String password);
}