package com.wizardry.qingyou.utils;

import org.springframework.mail.SimpleMailMessage;

public class SimpleMailMessageUtil {
    // 创建一个局部变量
    private static SimpleMailMessage simpleMailMessageutil;
    static{
        simpleMailMessageutil = new SimpleMailMessage();
    }

    public static SimpleMailMessage getSimpleMailMessageutil(){
        return simpleMailMessageutil;
    }
}
