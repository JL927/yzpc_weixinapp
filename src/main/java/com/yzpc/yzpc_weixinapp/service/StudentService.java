package com.yzpc.yzpc_weixinapp.service;

import com.yzpc.yzpc_weixinapp.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wq
* @description 针对表【student】的数据库操作Service
* @createDate 2024-12-20 16:59:59
*/
public interface StudentService extends IService<Student> {

    void addStudents(Student[] stds);

    Student login(String username, String password);

}
