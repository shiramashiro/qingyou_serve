package com.wizardry.qingyou.mapper;


import com.wizardry.qingyou.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
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

    /**
     *  通过电话验证用户是否存在
     * @param phone 用户电话
     * @return  一条用户数据
     */
    User findByUserPhone(String phone);

    /**
     *  通过邮箱验证用户是否存在
     * @param email 用户邮箱
     * @return  一条用户数据
     */
    User findByUserEmail(String email);

    /**
     * 根据用户的id来修改密码
     * @param id   用户的id
     * @return  受影响的行数
     */
    Integer updatePassword(Integer id, @Param("psw")String password);

    /**
     * 用户id的查询
     * @param id    用户的id
     * @return  一个用户的对象，反之null
     */
    User findByUid(Integer id);

}