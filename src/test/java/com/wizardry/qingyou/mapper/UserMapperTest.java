package com.wizardry.qingyou.mapper;

import com.wizardry.qingyou.entity.User;
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

public class UserMapperTest {
    @Autowired
    private UserMapper usermapper;

    @Test//插入功能的实现
    public void insert() {
        User user = new User();
        //设置用户名和密码
        user.setUname("WavesBright");
        user.setPsw("123123");
        Integer rows = usermapper.insert(user);
        System.out.println(rows);
    }

    @Test//查询功能的实现
    public void findByUsername() {
        User user = usermapper.findByUsername("WavesBright");
        System.out.println(user);
    }

    /**
     * 修改密码功能测试，密码需要加密过的密码才行
     */
    @Test
    public void updatePassword(){

        //Integer row = usermapper.updatePassword(31,"153433");
        //System.out.println(row);
    }

    /**
     *修改密码前缀，查询id，该功能无异常
     */
    @Test
    public void findByUid(){
        System.out.println(usermapper.findByUid(2));
    }

}


