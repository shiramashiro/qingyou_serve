package com.wizardry.qingyou.service;

public interface IUserCode {
    /**
     * 辨别验证码是否错误
     * @param userEmail 用户邮箱
     * @param code  用户输入的验证码
     * @return 不需要
     */
    void codeConfirm(String userEmail,String code);


    /**
     * 将验证码存入redis数据库，key值为用户的id
     * @param userEmail 用户的id
     */
    void StorageCode(String userEmail);


}
