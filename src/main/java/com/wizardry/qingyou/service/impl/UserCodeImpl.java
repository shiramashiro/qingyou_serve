package com.wizardry.qingyou.service.impl;

import com.wizardry.qingyou.service.IUserCode;
import com.wizardry.qingyou.utils.RedisUtils;
import com.wizardry.qingyou.utils.SimpleMailMessageUtil;
import com.wizardry.qingyou.utils.exceptions.CodeErrorException;
import com.wizardry.qingyou.utils.exceptions.CodeNotExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCodeImpl implements IUserCode {
    // 发件人
    @Value("${spring.mail.username}")
    private String from;

    @Autowired // 全局操纵发送我们的java邮件
    private JavaMailSender sender;

    @Autowired
    private RedisUtils redisResult;

    /**
     *  验证码的存储
     * @param userEmail 前端给予的令牌
     */
    @Override
    public void StorageCode(String userEmail) {
        //localhost:8080/mail/send?to=1336959829@qq.com&subject=验证码&content=晚上好
        //判断验证码是否过期
        Object codeWhether = redisResult.get(userEmail);
        System.out.println(codeWhether);
        if(codeWhether!=null){
            //验证码未过期
            throw new CodeNotExpiredException("验证码未过期");
        }
        //存储验证码--六位数字or六位数字+字母
        String code = UUID.randomUUID().toString().substring(0,5);
        //String code = (int)((Math.random() * 9 + 1)* 10000) +"";
        // 短消息对象
        SimpleMailMessage simpleMailMessage = SimpleMailMessageUtil.getSimpleMailMessageutil();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(userEmail);
        simpleMailMessage.setSubject("找回密码");
        simpleMailMessage.setText("您的验证码是："+code+",该验证码五分钟内有效，请及时使用");
        sender.send(simpleMailMessage);
        System.out.println("验证码发送成功！");
        //调用redis存储
        redisResult.set(userEmail,code,300);
        // 验证
        System.out.println("验证值为："+redisResult.get(userEmail));

    }

    /**
     * 验证码正误判断
     * @param userEmail 前端令牌
     * @param code  用户输入的验证码
     */
    @Override
    public void codeConfirm(String userEmail, String code) {
        String codeResult = String.valueOf(redisResult.get(userEmail));
        System.out.println("redis中存储的验证码为："+codeResult);
        if(!codeResult.equals(code)){
            // 验证码不正确
            throw new CodeErrorException("验证码错误");
        }
        if(codeResult==null){
            // 验证码过期
            throw new CodeErrorException("验证码过期");
        }
        System.out.println("验证码正确！");
    }
}
