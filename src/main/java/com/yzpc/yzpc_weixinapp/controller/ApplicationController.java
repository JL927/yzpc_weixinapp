package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Application;
import com.yzpc.yzpc_weixinapp.entity.enums.ApplicationTypes;
import com.yzpc.yzpc_weixinapp.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     * 本方法会设置初始化状态为 1
     * id自动生成
     * @return
     */
    @PostMapping("/application/add")
    public Result addApplication(@RequestBody Application application){
        application.setId(null);
        application.setStatus(1);
        String type = application.getApplicationType();
        //检测请求种类是否错误
        if(ApplicationTypes.fromValue(type) == null){
            return Result.error("请求种类错误");
        }else {
            applicationService.addApplication(application);
            return Result.success(application);
        }

    }
    @GetMapping("/application/getById") // ' /application/getById?studentId=1 '
    public Result getApplicationsById(@RequestParam(name = "studentId") Integer studentId){
        List<Application> applications = applicationService.getApplicationsById(studentId);
        return Result.success(applications);
    }

    /**
     * 根据申请的状态获取申请，用于不同角色审核不同状态的申请
     * @param status 状态
     * @return 申请列表
     */
    @GetMapping("/application/getByStatus") // ' /application/getByStatus?status=1 '
    public Result getApplicationByStatus(@RequestParam(name = "status") Integer status){
        List<Application> applications = applicationService.getApplicationsByStatus(status);
        return Result.success(applications);
    }

    /**
     * 修改申请状态
     * 申请状态中:
     * 0表示失败，
     * 1为提交等待班主任审核，
     * 2为等待主任审核，
     * 3为等待院长审核，
     * 4为通过，
     * 在通过后会有最终得分，
     * 如果失败则可以添加描述
     * @param application
     * @return
     */
    @PostMapping("/application/changeStatus")
    public Result changeStatus(@RequestBody Application application){
        if(ApplicationTypes.fromValue(application.getApplicationType()) == null)
            return Result.error("请求种类错误");


        applicationService.changeApplication(application);

        return Result.success();
    }
}
