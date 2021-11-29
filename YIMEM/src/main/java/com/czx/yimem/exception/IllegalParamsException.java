package com.czx.yimem.exception;

public class IllegalParamsException extends RuntimeException {

    /**
     *  自定义异常
     */
    private static final long serialVersionUID = 1L;

    public IllegalParamsException() {
        super();
    }

    public IllegalParamsException(String msg) {
        super(msg);
    }

}
