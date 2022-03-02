package com.wizardry.qingyou_serve.mapper;


import com.wizardry.qingyou_serve.entity.User;

/*用户模块的持久层接口*/
public interface UserMapper {
    /**
     * 注册用户
     *
     * @param user 用户的数据
     * @return 返回值为受影响的行数，增删改查都会使行数发生修改，可以根据返回值来查看是否修改成功
     */
    Integer insert(User user);

    /**
     * 查询用户是否注册
     *
     * @param Username 用户名
     * @return 如果找到，返回用户对应的信息，如果没有找到，返回空值
     */
    User findByUsername(String Username);


}