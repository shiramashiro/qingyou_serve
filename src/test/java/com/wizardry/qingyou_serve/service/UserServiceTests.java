package com.wizardry.qingyou_serve.service;

import com.wizardry.qingyou_serve.enity.User;
import com.wizardry.qingyou_serve.mapper.Usermapper;
import com.wizardry.qingyou_serve.service.Ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//自定义测试类注解
@SpringBootTest

@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;
    @Test//插入功能的实现
    public void reg(){
        try {
            //快捷键 ctrl+alt+T，选择try catch添加异常的捕获
            User user = new User();
            //设置用户名和密码
            user.setUname("黄先森");
            user.setPsw("123");
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
    //登录功能的测试
    @Test
    public void login(){
       User user =  userService.login("test01","010115");
       System.out.println(user);
    }

}


