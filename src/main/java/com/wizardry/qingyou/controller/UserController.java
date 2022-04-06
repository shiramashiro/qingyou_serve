package com.wizardry.qingyou.controller;

import com.wizardry.qingyou.controller.exceptions.FileEmptyException;
import com.wizardry.qingyou.controller.exceptions.FileSizeException;
import com.wizardry.qingyou.controller.exceptions.FileTypeException;
import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.service.impl.UserServiceImpl;
import com.wizardry.qingyou.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserController extends BaseController {
    // 用户模块业务层
    @Autowired
    private UserServiceImpl service;

    /**
     * 用户注册
     * @param user 用户数据
     * @return Json对象
     */
    @PostMapping("register")
    public JsonResult<Void> register(@RequestBody User user) {
        // 假设有异常产生会自动跳转到基类当中处理的方法，注册成功为2000
        return service.register(user);
    }


    /**
     * login请求的一些修改
     * @param user    用户对象
     * @param session 无论是哪种类型登录成功，最终只保存用户名的方式
     * @return Json对象
     */
    @PostMapping(path = "login")
    public JsonResult<User> login(@RequestBody User user, HttpSession session) {
        // 调用接口当中的login方法，该方法先进行查询用户的操作，查询完毕后返回一个user对象
        User data = service.login(user);
        System.out.println("登陆成功");
        // 向session对象中完成数据的绑定,将用户的id和用户名传递给session对象(该对象是全局的)
        session.setAttribute("id", data.getId());
        session.setAttribute("username", data.getUname());
        // 将这个user对象返回给前端
        return new JsonResult<>(loginSuccess, data);
    }


    /**
     * 用户修改密码
     * @param session     seesion对象
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Json对象
     */
    @PostMapping(path = "update/password")
    public JsonResult<Void> changePassword(HttpSession session,
                                           String oldPassword,
                                           String newPassword) {
        // session获取到用户的id
        Integer uid = getUidFromSession(session);
        // session获取到用户的uname
        service.UpdatePsw(uid, oldPassword, newPassword);
        return new JsonResult<>(2003);
    }

    @PostMapping("update/avatar")
    public JsonResult<String> updateAvatar(@RequestParam("uid") String uid,
                                           @RequestParam("uname") String uname,
                                           @RequestParam("file") MultipartFile file) {
        return service.updateAvatar(uid, uname, file);
    }


}