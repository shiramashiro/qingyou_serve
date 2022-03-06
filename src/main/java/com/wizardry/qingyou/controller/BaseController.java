package com.wizardry.qingyou.controller;

import com.wizardry.qingyou.utils.JsonResult;
import com.wizardry.qingyou.utils.exceptions.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/* 异常处理类的基类 */
public class BaseController {
    public final static Map<String,Integer> map = new HashMap();
    static {
        map.put("手机号不正确",5000 );
        map.put("用户名不正确",5001);
        map.put("电子邮箱不正确",5002);
        map.put("密码不正确",5003);
        map.put("手机号已存在",5004);
        map.put("用户名已存在",5005);
    }
    // 注册成功
     public static int regSuccess = 2001;
     // 登陆成功
     public static int loginSuccecc = 2002;
    //请求处理的方法
    //自动将异常对象传递给此方法的参数列表上
    //当项目中产生了异常，被统一拦截到此方法当中，这个方法就相当于处理方法，方法的返回值传递给前端
    @ExceptionHandler(ServiceException.class)// 这个注解修饰的的方法，用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        String msg = e.getMessage();
        Integer state = map.get(msg);
        System.out.println("错误信息为："+msg+"匹配到的状态码为："+state);
        result.setState(state);
        result.setMassage(msg);
        return result;
    }

    /**
     * 从session对象中获取用户id
     * 从哪一个session对象中获取？
     * @param session 临时存储用户数据的对象
     * @return session对象当中存储的uid
     */
    protected final Integer getUidFromSession(HttpSession session){

        return Integer.valueOf(session.getAttribute("id").toString());
    }

    /**
     * 获取当前用户的username
     * @param session session对象
     * @return 当前登录的用户名
     * 在HttpSession的实现类中重写了toString()方法,不是句柄信息输出
     */
    protected final String getUsernameFromSession(HttpSession session){

        return session.getAttribute("username").toString();
    }


}