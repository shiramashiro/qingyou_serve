package com.wizardry.qingyou.utils.exceptions;

import java.io.Serializable;

public class CodeErrorException extends ServiceException {
    public CodeErrorException() {
        super();
    }

    public CodeErrorException(String message) {
        super(message);
    }

}
