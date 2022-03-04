package com.wizardry.qingyou.service.impl;

import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.mapper.UserMapper;

import com.wizardry.qingyou.service.UserService;
import com.wizardry.qingyou.utils.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
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
     * @param user 用户对象
     * return 一个压缩过的用户数据
     */
    @Override
    public User login(User user) {
        // 获取密码-明文
        String psw = user.getPsw();
        // 验证用户的账号类型
        User result= usermapper.findByAccountType(user);
        if(result==null){
            throw new UserNotFoundException("没有查找到该用户");
        }
        //1.先获取用户自身加密过后的密码
        String MiPassword = result.getPsw();
        //2.获取用户自身的盐值
        String MiSalt = result.getSalt();
        //3.将参数密码和获取到的盐值进行md5的算法进行匹配
        String newMd5Password = md5Password(psw,MiSalt);
        if(!newMd5Password.equals(MiPassword)){
            //不正确，运行异常
            throw new PasswordNotMatchException("用户输入的密码错误异常");
        }
        
        // 用户信息压缩
        User user1 = new User();
        user1.setId(result.getId());
        user1.setUname(result.getUname());
        user1.setAvatar(result.getAvatar());
        return user1;

    }
    /*// 辅助辨别账号类型
    private User accountType(User result,String password){
        //1.先获取用户自身加密过后的密码
        String MiPassword = result.getPsw();
        System.out.println("获取到的密码为"+MiPassword);
        //2.获取用户自身的盐值
        String MiSalt = result.getSalt();
        //3.将参数密码和获取到的盐值进行md5的算法进行匹配
        String newMd5Password = md5Password(password,MiSalt);
        if(!newMd5Password.equals(MiPassword)){
            //不正确，运行异常
            throw new PasswordNotMatchException("用户输入的密码错误异常");
        }
        User user = new User();
        user.setId(result.getId());
        user.setUname(result.getUname());
        user.setAvatar(result.getAvatar());
        System.out.println(user);
        //返回这个用户数据是为了辅助其他页面作展示id，name，头像
        return user;
    }*/

    /**
     *   用户修改密码模块
     * @param id    用户id
     * @param oldpassword 用户旧密码
     * @param newPassword  用户新密码
     */
    @Override
    public void UpdatePsw(Integer id, String oldpassword,String newPassword) {
        // 查询用户是否存在，该用户不存在则抛出异常
        User result =  usermapper.findByUid(id);
        if(result == null){
            // 抛出用户查询不存在的异常--UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }
        // 不为空，判断密码是否和原始密码一致
        // 将参数密码和本身的盐值进行加密,得到现在加密后的密码
        String nowPsw = md5Password(oldpassword,result.getSalt());
        if(!result.getPsw().equals(nowPsw)){
            // 密码不相同，抛出密码错误异常
            throw new PasswordNotMatchException("用户输入的密码错误异常");
        }

        // 将新设置的密码进行加密，再调用修改密码的操作
        String newMd5Password = md5Password(newPassword,result.getSalt());

        // 执行修改密码的操作
        Integer rows = usermapper.updatePassword(id,newMd5Password);

        if(rows!=1){
            throw new UpdateException("在更新时产生未知的异常");
        }

    }

}