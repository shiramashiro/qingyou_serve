package com.wizardry.qingyou_serve.mapper;

import com.wizardry.qingyou_serve.enity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 当前类为测试类，不会随项目打包发送
@SpringBootTest
/**
 *  * RunWith:启动单元测试类（单元测试类是不能够运行的），需要穿第一个参数
 *  * 必须是SpringRunner的实例类型--.class
 *  * 必须要手动导一下，鼠标放上去，然后放入实体类，在导一次包
 */
@RunWith(SpringRunner.class)

public class UserMapperTests {
    @Autowired
    private Usermapper usermapper;
    
    @Test//插入功能的实现
    public void insert(){
        User user = new User();
        //设置用户名和密码
        user.setUname("黄先森2");
        user.setPsw("12312333");
        Integer rows = usermapper.insert(user);
        System.out.println(rows);
    }
    
    @Test//查询功能的实现
    public void findByUsername(){
        User user = usermapper.findByUsername("test01");
        System.out.println(user);
    }

}


