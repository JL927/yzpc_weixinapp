package com.yzpc.yzpc_weixinapp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzpc.yzpc_weixinapp.entity.Class;
import com.yzpc.yzpc_weixinapp.entity.Student;
import com.yzpc.yzpc_weixinapp.exception.BusinessException;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.service.StudentService;
import com.yzpc.yzpc_weixinapp.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public int updateScore(Long studentId, String index, Integer score) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",studentId);
        queryWrapper.select(index);

        Integer originScore = (Integer)this.getMap(queryWrapper).get(index);

        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",studentId);
        updateWrapper.set(index,score+originScore);

        return this.baseMapper.update(updateWrapper);
    }

    @Override
    public int updateTotalScore(Long studentId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",studentId);
        queryWrapper.select("major_id");

        Student student = this.baseMapper.selectOne(queryWrapper);
        Integer majorId = student.getMajorId();

        Integer total = this.baseMapper.getSumOfScore(studentId);

        //设置总分上限
        if(majorId == 3){
            if (total>6)
                total=6;
        }else {
            if (total>4)
                total=4;
        }



        UpdateWrapper<Student> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",studentId);
        updateWrapper.set("total_score",total);

        return this.baseMapper.update(updateWrapper);


    }

    @Override
    public int updateSatisfied(Long studentId) {
        QueryWrapper<Student> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("id",studentId);
        Student student = this.baseMapper.selectOne(queryWrapper);

        Integer majorId = student.getMajorId();
        Integer chineseScore = student.getChineseScore();
        Integer englishScore = student.getEnglishScore();
        Integer skillScore = student.getSkillScore();
        Integer computerScore = student.getComputerScore();
        Integer totalScore = student.getTotalScore();
        Integer satisfiedV0 = student.getSatisfied();

        int satisfied = 0;

        if (majorId.equals(1)
                || majorId.equals(2)
                || majorId.equals(4)
                || majorId.equals(5)
                || majorId.equals(6)
                || majorId.equals(7)
                || majorId.equals(8)
        ){
            if((englishScore>=2||computerScore>=2) && skillScore>=2 && totalScore>=4)
                satisfied=1;
        }else if (majorId.equals(3)){
            if(englishScore>=2 && computerScore>=2 && skillScore>=2 && totalScore>=6)
                satisfied=1;
        } else if (majorId.equals(9)|| majorId.equals(10)) {
            if(chineseScore>=1 && computerScore>=2 && skillScore>=1 && totalScore>=4)
                satisfied=1;
        }

        //如果数据与原始相同则不修改
        if(satisfiedV0.equals(satisfied)){
            return 0;
        }else {
            UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id",studentId);
            updateWrapper.set("satisfied",satisfied);

            return this.baseMapper.update(updateWrapper);
        }


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

    @Override
    public List<Student> getStudentsByClass(Integer classId) {
        QueryWrapper<Student> wrapper =new QueryWrapper<>();
        wrapper.eq("class_id",classId);

        List<Student> students = this.baseMapper.selectList(wrapper);
        if (students.isEmpty())
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"班级id错误或不存在");
        return students;
    }

    @Override
    public int changePassword(String username, String oldPassword , String newPassword) {
        login(username,oldPassword);

        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",username);
        updateWrapper.set("password",newPassword);

        int update = this.baseMapper.update(updateWrapper);

        return update;
    }

    @Override
    public int changeClass(Long id, Integer classId) {
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("class_id",classId);

        int update = this.baseMapper.update(updateWrapper);

        return update;
    }

    @Override
    public List<Long> getStudentsIdByTeacherId(int teacherId) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("teacher_id",teacherId);
        //查询实体返回id列表
        return this.baseMapper.selectObjs(wrapper)
                .stream()
                .map(id -> (Long) id)
                .collect(Collectors.toList());
    }


}




