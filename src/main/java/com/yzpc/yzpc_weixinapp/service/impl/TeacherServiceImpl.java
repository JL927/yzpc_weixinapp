package com.yzpc.yzpc_weixinapp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzpc.yzpc_weixinapp.entity.Teacher;
import com.yzpc.yzpc_weixinapp.mapper.TeacherMapper;
import com.yzpc.yzpc_weixinapp.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * @author wq
 * @description
 * @date 2024/12/18 21:04:55
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher> implements TeacherService {

    @Override
    public Teacher login(String username, String password) {
        if (StrUtil.hasBlank(username,password))
            throw new RuntimeException("用户名或密码为空");

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);

        Teacher teacher= this.baseMapper.selectOne(wrapper);

        if (teacher==null)
            throw new RuntimeException("用户不存在或者密码错误");

        return teacher;
    }
}
