package com.wizardry.qingyou.config;

import com.wizardry.qingyou.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/* 该类完成拦截器的注册,不注册的话拦截功能就无法实现 */
//@Configuration  //  加载当前的拦截器并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    /* 配置拦截器 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将自定义的拦截器进行注册
        HandlerInterceptor handler = new LoginInterceptor();
        //配置白名单，存在在一个List集合中，excludePathPatterns的参数要求
        List<String> patterns = new ArrayList<>();
        /*patterns.add("/bootstrap/**");  //  bootstrap放行*/
        patterns.add("/css/**");    //  css放行
        patterns.add("/js/**"); //  js放行
        patterns.add("/images/**"); //  images放行
        //  我这里暂时只放行登录和注册还有首页界面
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/web/首页.html");
        //两个url
        patterns.add("/users/reg");
        patterns.add("/users/login");
        /*其他部分后续添加*/

        // 将拦截器添加以完成注册
        registry.addInterceptor(handler)
                .addPathPatterns("/**")    //  该方法表示添加拦截路径的参数url是什么，/**代表该路径下所有的请求都会被拦截
                .excludePathPatterns(patterns);   //  除了什么不拦截
    }

}
