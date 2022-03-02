package com.wizardry.qingyou_serve.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<E> implements Serializable {
    //声明相关需要响应给前端的数据
    //状态码
    private Integer state;
    //描述信息
    private String massage;
    //返回信息，数据类型不清楚，采用泛型，根据情况定义
    private E data;

    //无参构造，便于调用
    public JsonResult() {

    }

    //涉及到状态码
    public JsonResult(Integer state) {
        this.state = state;
    }

    //关于异常捕获--构造方法
    public JsonResult(Throwable throwable) {
        //异常信息传递给massage
        this.massage = throwable.getMessage();
    }

    //状态码+对应的数据
    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }
}