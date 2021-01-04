package com.project.springboot.system.exception;

import java.util.Map;

/**
 * 用户名已存在异常
 */
public class UserNameAlreadyExistException extends BaseException {

    public UserNameAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}
