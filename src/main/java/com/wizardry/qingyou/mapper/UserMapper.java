package com.wizardry.qingyou.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizardry.qingyou.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 注册用户
     * @param user 用户的数据
     * @return 返回值为受影响的行数，增删改查都会使行数发生修改，可以根据返回值来查看是否修改成功
     */
    // Integer insertUser(User user);

    /**
     * 查询用户是否注册
     * @param uname 用户名
     * @return 如果找到，返回用户对应的信息，如果没有找到，返回空值
     */
    @Select("select*from users where uname= #{uname}")
    User findByUsername(String uname);

    /**
     * 查询用户手机号是否注册
     *
     * @param phone 用户的电话
     * @return 用户数据
     */
    @Select("select*from users where phone= #{phone}")
    User findByPhone(String phone);

    /**
     * 查询用户邮箱是否存在
     * @param email 用户的邮箱
     * @return 用户数据
     */
    @Select("select*from users where email= #{email}")
    User findByEmail(String email);

    /**
     * 验证用户的账号类型
     * @param user 用户对象
     * @return 一个数据库中的用户对象
     */
    User findByAccountType(User user);

    /**
     * 根据用户的id来修改密码
     *
     * @param id 用户的id
     * @return 受影响的行数
     */
    Integer updatePassword(Integer id, @Param("psw") String password);

    /**
     * @param id     前段传递的用户id
     * @param avatar 前端传递的用户头像数据
     * @return 受影响的行数
     */
    Integer updateAvatar(@Param("id") Integer id, @Param("avatar") String avatar);

}