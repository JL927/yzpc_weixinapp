package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Teacher;
import com.yzpc.yzpc_weixinapp.entity.TeacherLogin;
import com.yzpc.yzpc_weixinapp.entity.UserLogin;
import com.yzpc.yzpc_weixinapp.service.ApplicationService;
import com.yzpc.yzpc_weixinapp.service.ClassService;
import com.yzpc.yzpc_weixinapp.service.StudentService;
import com.yzpc.yzpc_weixinapp.service.TeacherService;
import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author wq
 * @description
 * @date 2024/12/18 21:45:01
 */
@CrossOrigin
@RestController
@Slf4j
public class TeacherController {
    @Resource
    public TeacherService teacherService;

    @Resource
    public ClassService classService;

    @Resource
    public StudentService studentService;

    @Resource
    public ApplicationService applicationService;

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
        data.put("role",teacher.getRole());

        return Result.success(TeacherLogin.getTeacherV0(teacher,JWTUtils.generateJWT(data)));
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
    @Transactional
    public Result deleteTeachers(@RequestBody Integer[] ids){
        teacherService.deleteTeachers(ids);
        //TODO(同时删除教师所属班级和学生和学生图片，学生请求)
        for (int id:ids) {
            int deletes = classService.deleteClassByTeacherId(id);
            log.info("删除教师"+id+"名下"+deletes+"个班级");
            List<Long> studentsId = studentService.getStudentsIdByTeacherId(id);
            deletes = 0;
            for (Long stuId:studentsId){
                deletes += applicationService.delete(stuId);
            }
            log.info("删除教师"+id+"名下"+deletes+"个申请");
            deletes = studentService.deleteStudentsByTeacherId(id);
            log.info("删除教师"+id+"名下"+deletes+"个学生");
        }
        return Result.success();
    }



}
