package com.wizardry.qingyou_serve.controller;

import com.wizardry.qingyou_serve.entity.User;
import com.wizardry.qingyou_serve.service.IUserService;
import com.wizardry.qingyou_serve.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//组合注解 Controller+ResponseBody
@RestController

//什么样的请求会被拦截到这个类当中
@RequestMapping("users")
public class UserController extends BaseController {
    // 基于业务层的接口
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("reg")
    //表示数据以Json的方式传递给前端
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        //假设有异常产生会自动跳转到基类当中处理的方法
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String uname, String psw) {
        User data = userService.login(uname, psw);
        return new JsonResult<>(OK, data);//可写可不写
    }

}