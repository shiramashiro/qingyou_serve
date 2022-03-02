package com.wizardry.qingyou.utils.exceptions;

/* 业务层异常基类 */
// 业务层出现异常只有一种情况，运行的时候出现异常，所以继承运行异常RuntimeException
public class ServiceException extends RuntimeException {

    // 直接抛出异常的时候使用
    public ServiceException() {
        super();
    }

    //**带参数的抛出异常方法,例如业务层逻辑出现异常**
    public ServiceException(String message) {
        super(message);
    }

    //**带参数，同时带对象，Throwable继承Object，出现异常的对象**
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    //指出异常对象
    public ServiceException(Throwable cause) {
        super(cause);
    }

    //全部信息全部抛出
    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}