package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Teacher;
import com.yzpc.yzpc_weixinapp.entity.UserLogin;
import com.yzpc.yzpc_weixinapp.service.TeacherService;
import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

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

    /**
     * 教职工登录，输入账号，密码，职位
     * @param userLogin
     * @return token
     */
    @PostMapping("/teacher/login")
    public Result login(@RequestBody UserLogin userLogin){
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        String role = userLogin.getRole();

        Teacher teacher = teacherService.login(username,password,role);

        HashMap<String,Object> data = new HashMap<>();
        data.put("data", teacher);

        return Result.success(JWTUtils.generateJWT(data));
    }

    /**
     * 添加单个或多个教职工，输入姓名，账号，密码，职位
     * @param teachers
     * @return
     */
    @PostMapping("/teacher/add")
    public Result addTeachers(@RequestBody Teacher[] teachers){
        teacherService.addTeachers(teachers);
        return Result.success();
    }

    /**
     * 谨慎操作！！！
     * 批量删除教师，输入id数组
     * @param ids
     * @return
     */
    @PostMapping("/teacher/delete")
    public Result deleteTeachers(@RequestBody Integer[] ids){
        teacherService.deleteTeachers(ids);
        //TODO(同时删除教师所属班级和学生)
        return Result.success();
    }



}
