package com.yzpc.yzpc_weixinapp.entity.enums;

/**
 * @author wq
 * @description 证书类型
 * @date 2024/12/18 21:27:52
 */
public enum ApplicationTypes {

    COMPUTER("computer"),
    SKILL("skill"),
    ENGLISH("english"),
    CHINESE("chinese");


    private final String value;

    ApplicationTypes(String applicationType) {
        this.value = applicationType;
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
