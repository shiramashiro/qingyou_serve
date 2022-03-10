package com.wizardry.qingyou.utils.exceptions;

/* 用户名被占用所产生的异常 */
public class UserIsOccupiedException extends ServiceException {
    public UserIsOccupiedException() {
        super();
    }

    public UserIsOccupiedException(String message) {
        super(message);
    }

    public UserIsOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsOccupiedException(Throwable cause) {
        super(cause);
    }

    protected UserIsOccupiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}