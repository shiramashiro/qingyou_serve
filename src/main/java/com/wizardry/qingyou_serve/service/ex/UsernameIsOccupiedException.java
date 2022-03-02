package com.wizardry.qingyou_serve.service.ex;

/* 用户名被占用所产生的异常 */
public class UsernameIsOccupiedException extends ServiceException {
    public UsernameIsOccupiedException() {
        super();
    }

    public UsernameIsOccupiedException(String message) {
        super(message);
    }

    public UsernameIsOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameIsOccupiedException(Throwable cause) {
        super(cause);
    }

    protected UsernameIsOccupiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}