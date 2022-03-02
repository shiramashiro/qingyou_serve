package com.wizardry.qingyou_serve.service.impl;

import com.wizardry.qingyou_serve.entity.User;
import com.wizardry.qingyou_serve.mapper.UserMapper;
import com.wizardry.qingyou_serve.service.ex.*;
import com.wizardry.qingyou_serve.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

// 交由Service管理
@Service
/* 用户模块业务层的实现类 */
public class UserServiceImpl implements IUserService {
    @Autowired//交给spring自动装配
    private UserMapper usermapper;

    /**
     * 用户注册-方法实现
     *
     * @param user 用户这个对象的数据
     */
    @Override
    public void reg(User user) {
        //查询该条数据是否存在
        String name = user.getUname();
        //1.调用查询方法,使用结果集
        User result = usermapper.findByUsername(name);
        //2.判断用户名是否被占用
        if (result != null) {
            //抛出用户名被占用的异常
            throw new UsernameIsOccupiedException("用户名被占用！");
        }

        //3.密码加密处理
        /**
         * md5算法，密码加密
         *      密码加密处理的实现，md5算法形式：67dasdsada-455dasdsdada-dwasd456das-456456
         * */
        String oldpsw = user.getPsw();
        //随机生成一个盐值,转换成字符，再大写
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体进行处理,调用自建加密算法
        String newpsw = md5Password(oldpsw, salt);
        //4.插入数据之前，补全剩下的信息
        Date date = new Date();
        user.setCreatedDate(date);
        //将新密码重新传递给user对象
        user.setPsw(newpsw);
        //传递盐值
        user.setSalt(salt);

        //5.插入数据,执行业务注册功能的实现(row==1)
        Integer rows = usermapper.insert(user);

        //6.插入不一定会成功，极小概率出现宕机
        if (rows != 1) {
            throw new InsertException("在用户注册的过程中产生了未知异常");
        }

    }

    //md5加密算法--供reg和其他该类调用
    private String md5Password(String password, String salt) {
        //加密三次，md5加密算法，先转换字节，在转换成大写
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    /**
     * 用户登录-方法实现
     *
     * @param username 用户名
     * @param password 用户密码
     */
    @Override
    public User login(String username, String password) {
        // 根据用户数据查询用户是否存在，如果不存在则抛出异常
        User result = usermapper.findByUsername(username);
        if (result == null) {
            //未能查询到，抛出异常
            throw new UsernameNotFoundException("用户名不存在");
        }
        //查询到用户，判断输入的密码是否匹配
        //1.先获取用户自身加密过后的密码
        String MiPassword = result.getPsw();
        //2.获取用户自身的盐值
        String MiSalt = result.getSalt();
        //3.将参数密码和获取到的盐值进行md5的算法进行匹配
        String newMd5Password = md5Password(password, MiSalt);
        if (!newMd5Password.equals(MiPassword)) {
            //不正确，运行异常
            throw new PasswordNotMatchException("用户密码错误异常");
        }
        //该字段讨论后确认是否添加
        /*
         * 这条字段为1代表删除了该用户，但是这条数据依旧存在
         * if(user.getIsDelete()==1){
         *       throw new UsernameNotFoundException("用户名不存在");
         * }
         *
         * */

        // 将用户数据量变小，重新new 一个用户,用户的数据量越小，程序的效率越高
        User user = new User();
        user.setId(result.getId());
        user.setUname(result.getUname());
        user.setAvatar(result.getAvatar());
        //返回这个用户数据是为了辅助其他页面作展示id，name，头像
        return user;
    }

}