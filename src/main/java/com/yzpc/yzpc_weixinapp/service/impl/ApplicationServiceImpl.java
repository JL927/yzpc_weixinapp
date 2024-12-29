package com.yzpc.yzpc_weixinapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzpc.yzpc_weixinapp.entity.Application;
import com.yzpc.yzpc_weixinapp.entity.Student;
import com.yzpc.yzpc_weixinapp.exception.BusinessException;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.service.ApplicationService;
import com.yzpc.yzpc_weixinapp.mapper.ApplicationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Application getApplicationsByOwnId(Long applicationId) {
        QueryWrapper<Application> wrapper =new QueryWrapper<>();
        wrapper.eq("id",applicationId);

        Application application = this.baseMapper.selectOne(wrapper);
        return application;
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
        if (Objects.nonNull(application) && Objects.nonNull(application.getId())) {
            UpdateWrapper<Application> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", application.getId());
            long count = this.baseMapper.selectCount(updateWrapper);
            if (count > 0) {
                updateWrapper.set("status", application.getStatus());
                updateWrapper.set("description", application.getDescription());
                updateWrapper.set("score", application.getScore());
                updateWrapper.set("name", application.getName());
                updateWrapper.set("application_type",application.getApplicationType());
                // 使用条件构造器进行更新
                this.baseMapper.update(null, updateWrapper);
            } else {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,
                        "不存在id为 " + application.getId() + " 的请求，无法修改");
            }
        }else {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
    }

    @Override
    public int delete(Long id) {
        QueryWrapper<Application> wrapper =new QueryWrapper<>();
        wrapper.eq("id",id);

        return this.baseMapper.delete(wrapper);
    }
}




