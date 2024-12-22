package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Application;
import com.yzpc.yzpc_weixinapp.entity.enums.ApplicationTypes;
import com.yzpc.yzpc_weixinapp.service.ApplicationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wq
 * @description 证书申请方法
 * @date 2024/12/21 15:10:42
 */
@CrossOrigin
@RestController
public class ApplicationController {

    @Resource
    public ApplicationService applicationService;

    /**
     * 新增请求，输入学生id，请求种类（'computer', 'skill', 'english', 'chinese'）
     * @return
     */
    @PostMapping("/application/add")
    public Result addApplication(@RequestBody Application application){
        application.setId(null);
        String type = application.getApplicationType();
        //检测请求种类是否错误
        if(ApplicationTypes.fromValue(type) == null){
            return Result.error("请求种类错误");
        }else {
            applicationService.addApplication(application);
            return Result.success(application);
        }

    }
}
