package com.yzpc.yzpc_weixinapp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzpc.yzpc_weixinapp.entity.Class;
import com.yzpc.yzpc_weixinapp.entity.Student;
import com.yzpc.yzpc_weixinapp.exception.BusinessException;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.service.StudentService;
import com.yzpc.yzpc_weixinapp.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
* @author wq
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-12-20 16:59:59
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Override
    public void addStudents(Student[] stds) {

        this.baseMapper.insert(Arrays.asList(stds));
    }

    @Override
    public int deleteStudentsByTeacherId(Integer teacherId) {
        QueryWrapper<Student> wrapper =new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);

        return this.baseMapper.delete(wrapper);
    }

    @Override
    public Student login(String username, String password) {
        if (StrUtil.hasBlank(username,password))
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码为空");

        QueryWrapper<Student> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);

        Student student = this.baseMapper.selectOne(queryWrapper);

        if (student==null)
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在或者密码错误");

        return student;

    }

}




