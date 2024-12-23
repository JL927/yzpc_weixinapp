package com.yzpc.yzpc_weixinapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzpc.yzpc_weixinapp.entity.Application;
import com.yzpc.yzpc_weixinapp.service.ApplicationService;
import com.yzpc.yzpc_weixinapp.mapper.ApplicationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wq
* @description 针对表【application】的数据库操作Service实现
* @createDate 2024-12-20 17:00:11
*/
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application>
    implements ApplicationService{

    @Override
    public void addApplication(Application application) {
        this.baseMapper.insert(application);
    }

    @Override
    public List<Application> getApplicationsById(Integer studentId) {

        QueryWrapper<Application> wrapper =new QueryWrapper<>();
        wrapper.eq("student_id",studentId);

        List<Application> applications = this.baseMapper.selectList(wrapper);
        return applications;
    }

    @Override
    public List<Application> getApplicationsByStatus(Integer status) {
        QueryWrapper<Application> wrapper =new QueryWrapper<>();
        wrapper.eq("status",status);

        List<Application> applications = this.baseMapper.selectList(wrapper);
        return applications;
    }

    @Override
    public void changeApplication(Application application) {

    }
}




