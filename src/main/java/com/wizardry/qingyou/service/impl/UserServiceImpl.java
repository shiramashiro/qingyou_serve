package com.wizardry.qingyou.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.mapper.UserMapper;

import com.wizardry.qingyou.service.IUserService;
import com.wizardry.qingyou.utils.JsonResult;
import com.wizardry.qingyou.utils.OSSUtil;
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
    private UserMapper usermapper;
    // OSS工具类
    @Autowired
    private OSSUtil ossUtil;

    /**
     * 用户注册-方法实现
     * @param user 用户这个对象的数据
     */
    @Override
    public void register(User user) {
        // 查询该用户是否存在-用户名查询
        User userInfo = usermapper.findByUsername(user.getUname());
        if(userInfo != null){
            throw new UserIsOccupiedException("用户名已存在");
        }else{
            User resultPhone = usermapper.findByPhone(user.getPhone());
            if(resultPhone != null){
                throw new UserIsOccupiedException("手机号已存在");
            }
            //随机生成一个盐值,转换成字符，再大写
            String salt = UUID.randomUUID().toString().toUpperCase();
            //将密码和盐值作为一个整体进行处理,调用自建加密算法
            String newpsw = md5Password(user.getPsw(), salt);
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
                throw new InsertException("注册过程发生未知异常");
            }

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
     * @param user 用户对象
     * return 一个压缩过的用户数据
     */
    @Override
    public User login(User user) {
        // 获取密码-明文
        String psw = user.getPsw();
        // 用户的登录方式
        String actype =AccountType(user);
        // 验证用户的账号类型
        User result=null;
        switch(actype){
            case "uname":
                result = usermapper.findByAccountType(user);
                if(result==null){
                    throw new UserNotFoundException("用户名不正确");
                }
                // 调用加密算法
                AcctypePsw(result,psw);
                break;
            case "phone":
                result = usermapper.findByAccountType(user);
                if(result==null){
                    throw new UserNotFoundException("手机号不正确");
                }
                AcctypePsw(result,psw);
                break;
            case "email":
                result = usermapper.findByAccountType(user);
                if(result==null){
                    throw new UserNotFoundException("电子邮箱不正确");
                }
                AcctypePsw(result,psw);
                break;
            default:
                break;
        }
        return result;
    }

    // 验证加密
    private User AcctypePsw(User user,String psw){
        //3.将参数密码和获取到的盐值进行md5的算法进行匹配
        String newMd5Password = md5Password(psw,user.getSalt());
        if(!newMd5Password.equals(user.getPsw())){
            //不正确，运行异常
            throw new PasswordNotMatchException("密码不正确");
        }
        // 用户信息压缩
        User user1 = new User();
        user1.setId(user.getId());
        user1.setUname(user.getUname());
        user1.setAvatar(user.getAvatar());
        return user1;
    }

    // 用户账号登录类型
    private String AccountType(User user){
        if(user.getUname()==null){
            if(user.getPhone()==null){
                    System.out.println("为邮箱登录");
                    return "email";
            }else{
                System.out.println("电话登录");
                return "phone";
            }
        }else{
            System.out.println("用户名登录");
            return "uname";
        }

    }

    /**
     * 用户修改密码模块
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

    /**
     * 用户修改头像模块
     * @param objName 完整路径名
     * @param file 用户头像的文件
     * @return json串
     */
    @Override
    public JsonResult<String> uploadAvatar(String uid,String objName, MultipartFile file) {
        OSS oss = ossUtil.buildOSSClient();
        try {
            oss.putObject(ossUtil.packObject(objName,file));
        } catch (OSSException | ClientException | IOException oe) {
            return new JsonResult<>(500,"the server occurred error while uploading file failed.");
        } finally{
            if (oss!=null) {
                oss.shutdown();
            }
        }
        // 莫得问题就传递数据了
        String avatar = "https://norza.cn/"+objName;
        Integer row = usermapper.updateAvatar(Integer.valueOf(uid),avatar);
        System.out.println("影响行数为："+row);
        return new JsonResult<>(2004,"https://norza.cn/"+objName);
    }
}