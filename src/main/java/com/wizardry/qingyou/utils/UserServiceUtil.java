package com.wizardry.qingyou.utils;

import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.mapper.UserMapper;
import com.wizardry.qingyou.utils.exceptions.PasswordNotMatchException;
import com.wizardry.qingyou.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Component
// 用户模块业务工具类
public class UserServiceUtil {

    // md5算法加密
    public String md5Password(String password, String salt) {

        //加密三次，md5加密算法，先转换字节，在转换成大写
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    // 判断用户登录的账号类型
    public String accountType(User user){
        if(user.getUname()!=null){
            return  "用户名不正确";
        }
        if(user.getPhone()!=null){
            return "手机号不正确";
        }
        return "邮箱不正确";
    }

    // 用户信息压缩
    public User resultUser(User user){
        // 用户信息压缩
        User user1 = new User();
        user1.setId(user.getId());
        user1.setUname(user.getUname());
        user1.setAvatar(user.getAvatar());
        return user1;
    }

    // 生成盐值，传递密码
    public User pswAndSalt(User user){
        //随机生成一个盐值,转换成字符，再大写
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体进行处理,调用自建加密算法
        String newpsw = md5Password(user.getPsw(), salt);

        //将新密码重新传递给user对象
        user.setPsw(newpsw);
        //传递盐值
        user.setSalt(salt);
        return user;
    }


}
