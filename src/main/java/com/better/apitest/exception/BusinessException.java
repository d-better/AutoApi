package com.better.apitest.exception;

import com.better.apitest.enums.ErrorEnum;

/**
 * 业务通用异常类
 */
public class BusinessException extends RuntimeException{

    public ErrorEnum errorEnum;
    private String errorMessage;


    public BusinessException (ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
        this.errorMessage = errorEnum.getName();
    }

    public BusinessException(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
