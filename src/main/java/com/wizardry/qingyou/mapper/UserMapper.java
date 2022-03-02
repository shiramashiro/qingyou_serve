package com.wizardry.qingyou.mapper;


import com.wizardry.qingyou.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
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
     * @param uname 用户名
     * @return 如果找到，返回用户对应的信息，如果没有找到，返回空值
     */
    User findByUsername(@Param("uname") String uname);

}