package com.yzpc.yzpc_weixinapp.entity.enums;

/**
 * @author wq
 * @description 职位
 * @date 2024/12/18 21:25:32
 */
public enum Role {
    DEAN("dean"),
    DEPARTMENT_HEAD("department_head"),
    CLASS_TEACHER("class_teacher");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // 反向查找
    public static Role fromValue(String value) {
        for (Role role : Role.values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
