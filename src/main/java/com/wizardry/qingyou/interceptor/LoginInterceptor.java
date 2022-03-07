package com.wizardry.qingyou.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 用户登录拦截器 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，没有则重定向到登录页面
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器（url+Controller：映射）
     * @return 如果返回值为true，表示放行当前的请求，返回值为false，
     * 表示拦截当前的请求
     * @throws Exception
     */

    //重写了preHandler后Springboot会自动把请求对象和响应对象传递给这个方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest来获取session对象
        Object obj = request.getSession().getAttribute("id");
        if (obj == null) {
            //用户没有进行登录操作，重定向到登录页面
            response.sendRedirect("/web/登录页.html");
            //结束后续的调用
            return false;
        }
        //没有执行if就放行
        return true;
    }

}
