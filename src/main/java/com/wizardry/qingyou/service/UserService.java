package com.wizardry.qingyou.service;


import com.wizardry.qingyou.entity.User;

/**
 * 用户模块业务层接口
 */
public interface UserService {

    /**
     * 用户注册方法
     *
     * @param user 用户这个对象的数据
     */
    void register(User user);

    /**
     *  用户登录
     * @param user 用户对象
     * @return  返回的是一个用户对象，如果没有查询到用户则返回null
     */
    User login(User user);

    /**
     * 用户修改密码
     * @param id    用户id
     * @param password  用户原始密码
     * @param newPassword  用户新密码
     * @return  返回一个用户对象，没有查询到用户返回null
     */

    void UpdatePsw(Integer id,String password,String newPassword);

    

}