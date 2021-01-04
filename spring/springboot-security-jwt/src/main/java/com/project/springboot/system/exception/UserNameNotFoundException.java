package com.project.springboot.system.exception;

import java.util.Map;

/**
 * 找不到用户名
 */
public class UserNameNotFoundException extends BaseException {
    public UserNameNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}
