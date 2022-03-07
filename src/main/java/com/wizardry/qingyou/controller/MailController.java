package com.wizardry.qingyou.controller;

import com.wizardry.qingyou.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 邮件相关
@RestController
@CrossOrigin
@RequestMapping("mail")

public class MailController extends BaseController {
    // 发件人
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    // 全局操纵发送我们的java邮件
    private JavaMailSender sender;

    @RequestMapping("send")
    public JsonResult<Void> send(String to, String subject, String content) {
        System.out.println("发件人为" + from);
        // 短消息对象
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        sender.send(simpleMailMessage);
        return new JsonResult<>(2003);
    }

}
