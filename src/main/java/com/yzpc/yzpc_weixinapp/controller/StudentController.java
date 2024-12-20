package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Student;
import com.yzpc.yzpc_weixinapp.service.StudentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wq
 * @description 学生方法
 * @date 2024/12/20 17:14:20
 */
@CrossOrigin
@RestController
public class StudentController {

    @Resource
    public StudentService studentService;

    /**
     * 批量添加学生，必须值有name,username,teacherId,classId,majorId,密码默认888888
     * @param stds
     * @return
     */
    @PostMapping("/student/add")
    public Result addStudents(@RequestBody Student[] stds){
        studentService.addStudents(stds);

        return Result.success();
    }
}
