package com.yzpc.yzpc_weixinapp.service;

import com.yzpc.yzpc_weixinapp.entity.Application;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzpc.yzpc_weixinapp.entity.StatusList;

import java.util.List;

/**
* @author wq
* @description 针对表【application】的数据库操作Service
* @createDate 2024-12-20 17:00:11
*/
public interface ApplicationService extends IService<Application> {

    void addApplication(Application application);

    List<Application> getApplicationsById(Long studentId);

    Application getApplicationsByOwnId(Long applicationId);

    List<Application> getApplicationsByStatus(Integer status);

    void changeApplication(Application application);

    int delete(Long id);

    int changeApplicationList(StatusList statusList);

}
