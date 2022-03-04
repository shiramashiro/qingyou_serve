package com.wizardry.qingyou.controller;

import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.service.impl.UserServiceImpl;
import com.wizardry.qingyou.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("reg")
    // 表示数据以Json的方式传递给前端
    public JsonResult<Void> reg(@RequestBody User user) {
        userService.reg(user);
        // 假设有异常产生会自动跳转到基类当中处理的方法
        return new JsonResult<>(OK);
    }

    /** 一、对于此注解 RequestMapping 中的 path
     属性说明：path 是一个字符串数字，在注解中，属性接收数组用 {} 表示。
     比如 path = {"a", "b"} 表示，此接口可以被
     http://localhost:8080/users/login 和
     http://localhost:8080/users/a 访问到。
     一般来说 path 就接收一个请求路径就行了。
     因此可以简化成 path = "login"（实际可以写 path = {"login"}。
     二、RequestMapping 是结合 GetMapping 和 PostMapping 的一个注解。
     一个完整的接口应该包含 params、path、method、consumes。
     params 指定请求 URL 中所包含的参数，
     http://localhost:8080/users/login?uname=xxx&psw=xxx。
     method 指定该请求是什么类型。consumes 指定请求数据是什么类型的数据。
     如果前端发来的数据是 json 格式的，
     那就用MediaType.APPLICATION_JSON_VALUE。
     三、这个接口应该是 POST，因为GET是获取资源的接口，
     不是提交数据的接口。再者，GET请求的数据能够明文地看到，
     而 POST 不能。所以 POST 安全一些。*/
    /**
     * login请求的一些修改
     * @param user 用户对象
     * @param session   无论是哪种类型登录成功，最终只保存用户名的方式
     * @return  Json对象
     */
    @RequestMapping(path = {"login"}, method = RequestMethod.POST)
    public JsonResult<User> login(@RequestBody User user,HttpSession session) {
        // 调用接口当中的login方法，该方法先进行查询用户的操作，查询完毕后返回一个user对象
        User data = userService.login(user);
        //向session对象中完成数据的绑定,将用户的id和用户名传递给session对象(该对象是全局的)
        session.setAttribute("id",data.getId());
        session.setAttribute("username",data.getUname());

        // 保存在session中的参数
        System.out.println("该用户的id为："+session.getAttribute("id"));
        System.out.println("该用户的uname为："+session.getAttribute("username"));
        //将这个user对象返回给前端，只能在该页面进行
        return new JsonResult<>(OK,data);//可写可不写
    }



    // 映射一个请求路径
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(HttpSession session,
                                           String oldPassword,
                                           String newPassword){
        // session获取到用户的id
        Integer uid = getUidFromSession(session);
        // session获取到用户的uname
        userService.UpdatePsw(uid,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }


}