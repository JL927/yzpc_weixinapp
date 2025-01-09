package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Application;
import com.yzpc.yzpc_weixinapp.entity.Student;
import com.yzpc.yzpc_weixinapp.entity.StudentLogin;
import com.yzpc.yzpc_weixinapp.entity.UserLogin;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.service.StudentService;
import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * 学生登录，使用账号，密码
     * @param userLogin
     * @return
     */
    @PostMapping("/student/login")
    public Result studentLogin(@RequestBody UserLogin userLogin){
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        Student student = studentService.login(username,password);

        HashMap<String,Object> data = new HashMap<>();
        data.put("data",student);
        data.put("role","student");

        return Result.success(StudentLogin.getStudentV0(student,JWTUtils.generateJWT(data)));
    }

    /**
     * 获取班级学生
     * @param classId 班级id
     * @return
     */
    @GetMapping("/student/getByClass")
    public Result getStudentsByClass(@RequestParam(name = "classId") Integer classId){
        List<Student> students = studentService.getStudentsByClass(classId);

        List<StudentLogin> studentsV0 = new ArrayList<>();

        for (Student student:students){
            studentsV0.add(StudentLogin.getStudentV0(student,null));
        }

        return Result.success(studentsV0);
    }

    /**
     * 获取所有学生
     * @return
     */
    @GetMapping("/student/getAll")
    public Result getStudentsAll(){
        List<Student> students = studentService.list();
        if (students.isEmpty())
            return Result.error(ErrorCode.NOT_FOUND_ERROR,"数据不存在");

        List<StudentLogin> studentsV0 = new ArrayList<>();

        for (Student student:students){
            studentsV0.add(StudentLogin.getStudentV0(student,null));
        }

        return Result.success(studentsV0);

    }
    @GetMapping("/student/getById")
    public Result getStudentById(@RequestParam(name = "studentId") Long studentId){
        Student student = studentService.getStudentById(studentId);
        if (student == null)
            return Result.error(ErrorCode.NOT_FOUND_ERROR,"不存在此id学生");
        return Result.success();
    }

    /**
     * 检查学生成绩是否达标，需要手动调用此接口才会更新满足字段
     * @param ids 学生id列表
     * @return
     */
    @PutMapping("/student/checkSatisfied")
    public Result checkSatisfied(@RequestBody List<Long> ids){
        int sum = 0;
        for (Long id:ids)
            sum += studentService.updateSatisfied(id);

        return Result.success("成功修改"+sum+"条数据");
    }

    @PostMapping("/student/changePassword")
    public Result changePassword(@RequestBody String username,
                                 @RequestBody String oldPassword,
                                 @RequestBody String newPassword){

        int i = studentService.changePassword(username,oldPassword,newPassword);
        return Result.success("成功修改"+i+"条数据");

    }

    /**
     * 将一批学生的班级修改
     * @param studentIds 学生id
     * @param classId 班级id
     * @return
     */
    @PutMapping("/student/changeClass")
    public Result changeClass(@RequestBody List<Long> studentIds,
                              @RequestBody Integer classId){

        int sum = 0;
        for (Long id:studentIds)
            sum += studentService.changeClass(id,classId);

        return Result.success("成功修改"+sum+"条数据");

    }




}
