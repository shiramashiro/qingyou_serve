package com.wizardry.qingyou_serve.controller;

import com.wizardry.qingyou_serve.service.ex.*;
import com.wizardry.qingyou_serve.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* 异常处理类的基类 */
public class BaseController {
    // 统一将操作成功的状态码规定为200，该状态码可修改
    public static int OK = 200;
    //请求处理的方法
    //自动将异常对象传递给此方法的参数列表上
    //当项目中产生了异常，被统一拦截到此方法当中，这个方法就相当于处理方法，方法的返回值传递给前端

    @ExceptionHandler(ServiceException.class)// 这个注解修饰的的方法，用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameIsOccupiedException) {
            //如果这个异常属于用户名异常
            result.setState(4000);//状态码4000
            result.setMassage("用户名已经被占用！");
        } else if (e instanceof UsernameNotFoundException) {
            result.setState(5001);//状态码5000
            result.setMassage("用户名查询不存在的异常！");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);//状态码5000
            result.setMassage("用户输入密码错误的异常");
        } else if (e instanceof InsertException) {
            result.setState(5000);//状态码5000
            result.setMassage("插入过程中发生未知异常！");
        }
        return result;
    }

}