package com.yzpc.yzpc_weixinapp.service;

import com.yzpc.yzpc_weixinapp.entity.Teacher;
import org.springframework.stereotype.Service;

/**
 * @author wq
 * @description 用户管理
 * @date 2024/12/18 21:03:47
 */
@Service
public interface TeacherService {

    Teacher login(String username, String password);
}
