package com.wizardry.qingyou.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.mapper.UserMapper;

import com.wizardry.qingyou.service.IUserService;
import com.wizardry.qingyou.utils.JsonResult;
import com.wizardry.qingyou.utils.OSSUtil;
import com.wizardry.qingyou.utils.UserServiceUtil;
import com.wizardry.qingyou.utils.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    // mapper层对象
    @Autowired
    private UserMapper userMapper;
    // OSS工具类
    @Autowired
    private OSSUtil ossUtil;
    // 用户模块工具类
    @Autowired
    private UserServiceUtil serviceUtil;


    /**
     * 用户注册-方法实现
     * @param user 用户这个对象的数据
     */
    @Override
    public JsonResult<Void> register(User user) {
        // 查询该用户是否存在-用户名查询
        User userInfo = userMapper.findByUsername(user.getUname());
        if (userInfo != null) {
            throw new UserIsOccupiedException("用户名已存在");
        }
        User resultPhone = userMapper.findByPhone(user.getPhone());
        if (resultPhone != null) {
            throw new UserIsOccupiedException("手机号已存在");
        }

        // 加密
        User user1 = serviceUtil.pswAndSalt(user);

        //5.插入数据
        int insert = userMapper.insert(user1);
        //6.宕机
        if (insert != 1) {
            throw new InsertException("注册过程发生未知异常");
        }

        return new JsonResult<>(2001);
    }

    /**
     * 用户登录-方法实现
     * @param user 用户对象
     * return 一个压缩过的用户数据
     */
    @Override
    public User login(User user) {
        // 1、调用工具类确认账号类型
        String accountError = serviceUtil.accountType(user);
        // 2、调用底层查询用户数据
        User byAccountType = userMapper.findByAccountType(user);
        // 3、用户不存在
        if(byAccountType==null){
            throw new UserNotFoundException(accountError);
        }

        // 4、将参数密码和获取到的盐值进行md5的算法进行匹配
        String newMd5Password = serviceUtil.md5Password(user.getPsw(),
                byAccountType.getSalt());
        System.out.println("用户输入的密码是："+user.getPsw()+
                "\n"+"数据库中的密码是："+
                byAccountType.getPsw()+"\n加密后的密码为："+newMd5Password);
        // 5、匹配失败
        if (!newMd5Password.equals(byAccountType.getPsw())) {
            throw new PasswordNotMatchException("密码不正确");
        }
        // 6、压缩过后的用户数据
        return serviceUtil.resultUser(byAccountType);
    }


    /**
     * 用户修改密码模块
     * @param id          用户id
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     */
    @Override
    public void UpdatePsw(Integer id, String oldPassword, String newPassword) {
        // 查询用户是否存在，该用户不存在则抛出异常
        User result = userMapper.selectById(id);
        if (result == null) {
            // 抛出用户查询不存在的异常--UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }
        // 不为空，判断密码是否和原始密码一致
        // 将参数密码和本身的盐值进行加密,得到现在加密后的密码
        String nowPsw = serviceUtil.md5Password(oldPassword, result.getSalt());
        if (!result.getPsw().equals(nowPsw)) {
            // 密码不相同，抛出密码错误异常
            throw new PasswordNotMatchException("用户输入的密码错误异常");
        }

        // 将新设置的密码进行加密，再调用修改密码的操作
        newPassword = serviceUtil.md5Password(newPassword, result.getSalt());

        // 执行修改密码的操作
        Integer rows = userMapper.updatePassword(id, newPassword);

        if (rows != 1) {
            throw new UpdateException("在更新时产生未知的异常");
        }
    }

    /**
     * 用户修改头像模块
     * @param uname 用户名
     * @param file  文件
     * @return json
     */
    @Override
    public JsonResult<String> updateAvatar(String uid, String uname, MultipartFile file) {
        OSS oss = ossUtil.buildOSSClient();
        String objName = "wizardry/imgs/avatars/" + uid + "/" + uname + ".jpg";
        try {
            oss.putObject(ossUtil.packObject(objName, file));
        } catch (OSSException | ClientException | IOException oe) {
            return new JsonResult<>(500, "the server occurred error while uploading file failed.");
        } finally {
            if (oss != null) oss.shutdown();
        }
        String avatarPath = "http://norza.cn/" + objName;
//        usermapper.updateAvatar(Integer.valueOf(uid), avatarPath);
        return new JsonResult<>(2004, avatarPath);
    }

    /**
     * 修改用户的各项信息
     * @param user    用户对象
     * @param state   状态码
     * @return Json串
     */
    @Override
    public JsonResult<Void> updateInformation(User user,Integer state) {
        // MP的动态SQL，里面有什么实体属性修改库中对应的属性
        int i = userMapper.updateById(user);
        if(i!=1){
            // 修改用户数据时发生未知异常
            throw new UpdateException("在更新时产生未知的异常");
        }
        return new JsonResult<>(state);
    }
}