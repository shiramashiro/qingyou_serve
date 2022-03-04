package com.wizardry.qingyou.service;

import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.utils.exceptions.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//自定义测试类注解
@SpringBootTest

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    //插入功能单元测试
    @Test
    public void reg(){
        try {
            //快捷键 ctrl+alt+T，选择try catch添加异常的捕获
            User user = new User();
            //设置用户名和密码
            user.setUname("WavesBright1");
            user.setPsw("010115");
            userService.reg(user);
            //如果插入成功
            System.out.println("OK");
            System.out.println(user);
        } catch (ServiceException svex) {
            //因为业务层可能会有两个异常，这个异常类不要写Exception那么笼统，两个异常
            //均继承自ServiceException
            //获取异常的类的对象，在获取类的名称
            System.out.println(svex.getClass().getName());
            //获取异常的信息
            System.out.println(svex.getMessage());
        }
    }
    //登录功能单元测试
    @Test
    public void login(){
       User user =  userService.login("2819938960@qq.com","111111");
       System.out.println(user);
    }

    // 修改密码功能单元测试
    @Test
    public void changePassword(){
        // 测试数据为id为38的，test08数据，密码6个1
        userService.UpdatePsw(3,"010115","111111");
    }

}


