package com.yzpc.yzpc_weixinapp.entity.enums;

import lombok.Getter;

/**
 * @author wq
 * @description 职位
 * @date 2024/12/18 21:25:32
 */
@Getter
public enum Role {
    DEAN("dean"),
    DEPARTMENT_HEAD("department_head"),
    CLASS_TEACHER("class_teacher");

    private final String value;

    Role(String value) {
        this.value = value;
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

    public static boolean isRole(String value){
        try{
            Role.fromValue(value);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
