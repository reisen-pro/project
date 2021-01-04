package com.project.springboot.system.exception;

import java.util.Map;

/**
 * 角色不存在异常
 */
public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException(Map<String, Object> data) {
        super(ErrorCode.Role_NOT_FOUND, data);
    }
}
