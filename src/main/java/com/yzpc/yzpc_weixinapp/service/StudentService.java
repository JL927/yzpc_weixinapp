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

    int deleteStudentsByTeacherId(Integer teacherId);

    //更新不同种类申请得分
    int updateScore(Long studentId,String index,Integer score);
    //更新总分
    int updateTotalScore(Long studentId);
    //更新是否合格
    int updateSatisfied(Long studentId);

    Student login(String username, String password);

}
