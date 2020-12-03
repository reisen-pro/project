package com.project.spring.mvc.exception;

/**
 * 自定义异常
 */
public class CustomException extends Exception{

    // 异常信息
    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
