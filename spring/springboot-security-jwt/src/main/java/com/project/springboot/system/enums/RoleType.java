package com.project.springboot.system.enums;

import lombok.Getter;


@Getter
public enum RoleType {
    USER("USER", "用户"),
    TEMP_USER("TEMP_USER", "临时用户"),
    MANAGER("MANAGER", "管理者"),
    ADMIN("ADMIN", "Admin");
    private final String name;
    private final String description;

    RoleType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getRoleDesc(String name) {
        for (RoleType value : RoleType.values()) {
            if (value.getName().equals(name)) {
                return value.getDescription();
            }
        }
        return null;
    }
}
