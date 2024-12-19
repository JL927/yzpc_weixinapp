package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Teacher;
import com.yzpc.yzpc_weixinapp.entity.UserLogin;
import com.yzpc.yzpc_weixinapp.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wq
 * @description
 * @date 2024/12/18 21:45:01
 */
@CrossOrigin
@RestController
public class TeacherController {
    @Resource
    public TeacherService teacherService;

    @PostMapping("/teacher/login")
    public Result Login(@RequestBody UserLogin userLogin){
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();

        Teacher teacher = teacherService.login(username,password);
        return Result.success(teacher);
    }
}
