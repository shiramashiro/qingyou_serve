package com.wizardry.qingyou.controller;

import com.wizardry.qingyou.service.impl.UserCodeimpl;
import com.wizardry.qingyou.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 邮件相关
@RestController
@CrossOrigin
@RequestMapping("mail")
public class MailController extends BaseController {
    @Autowired
    private UserCodeimpl usercodimpl;
    // 发送验证码
    @RequestMapping("send")
    public JsonResult<Void> send(String userEmail){
        // 目前就给用户的邮箱
        usercodimpl.StorageCode(userEmail);
        //验证码发送成功！
        return new JsonResult<>(2003);
        //return "success";
    }
    @RequestMapping("confirm")
    // 得到验证码进行判断
    public JsonResult<Void> identification(String userEmail,String code){
        usercodimpl.codeConfirm(userEmail,code);
        return new JsonResult<>(2004);
    }

}
