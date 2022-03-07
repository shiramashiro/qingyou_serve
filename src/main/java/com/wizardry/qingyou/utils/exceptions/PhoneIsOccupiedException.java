package com.wizardry.qingyou.utils.exceptions;

public class PhoneIsOccupiedException extends ServiceException {
    public PhoneIsOccupiedException() {
        super();
    }

    public PhoneIsOccupiedException(String message) {
        super(message);
    }

    public PhoneIsOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneIsOccupiedException(Throwable cause) {
        super(cause);
    }

    protected PhoneIsOccupiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
