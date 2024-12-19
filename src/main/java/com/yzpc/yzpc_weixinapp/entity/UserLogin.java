package com.yzpc.yzpc_weixinapp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wq
 * @description 用户登录类
 * @date 2024/12/18 21:55:21
 */
@Data
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 5749670829328720922L;

    private String username;
    private String password;
}
