package com.wizardry.qingyou.controller;

import com.wizardry.qingyou.service.impl.UserCodeImpl;
import com.wizardry.qingyou.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 邮件相关
@RestController
@CrossOrigin
@RequestMapping("mail")
public class MailController extends BaseController {
    @Autowired
    private UserCodeImpl usercodImpl;

    // 发送验证码
    @GetMapping("send")
    public JsonResult<Void> send(String userEmail){
        // 目前就给用户的邮箱
        usercodImpl.StorageCode(userEmail);
        //验证码发送成功！
        return new JsonResult<>(2003);
        //return "success";
    }

    @PostMapping("confirm")
    // 得到验证码进行判断
    public JsonResult<Void> identification(String userEmail,String code){
        usercodImpl.codeConfirm(userEmail,code);
        return new JsonResult<>(2004);
    }

}
