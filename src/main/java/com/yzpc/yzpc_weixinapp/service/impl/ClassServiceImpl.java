package com.yzpc.yzpc_weixinapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzpc.yzpc_weixinapp.entity.Class;
import com.yzpc.yzpc_weixinapp.exception.BusinessException;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.service.ClassService;
import com.yzpc.yzpc_weixinapp.mapper.ClassMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author wq
* @description 针对表【class】的数据库操作Service实现
* @createDate 2024-12-20 15:47:16
*/
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class>
    implements ClassService{

    @Override
    public void addClass(Class newClass) {
        this.baseMapper.insert(newClass);
    }

    @Override
    public int deleteClass(Integer classId) {
        return this.baseMapper.deleteById(classId);
    }

    @Override
    public int deleteClassByTeacherId(Integer teacherId) {

        QueryWrapper<Class> wrapper =new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);

        return this.baseMapper.delete(wrapper);
    }

    @Override
    public List<Class> findClassesByTeacherId(Integer teacherId) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
//        wrapper.select("id");
        wrapper.eq("teacher_id",teacherId);
        //查询实体返回id列表
//        return this.baseMapper.selectObjs(wrapper)
//                .stream()
//                .map(id -> (Integer) id)
//                .collect(Collectors.toList());

        List<Class> classes = this.baseMapper.selectList(wrapper);
        if (classes.isEmpty())
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"老师id错误或不存在此老师的班级");

        return classes;

    }

    @Override
    public List<Class> findClassesByMajorId(Integer majorId) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("major_id",majorId );
        List<Class> classes = this.baseMapper.selectList(wrapper);
        if (classes.isEmpty())
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"专业id错误或不存在此专业的班级");

        return classes;


    }
}




