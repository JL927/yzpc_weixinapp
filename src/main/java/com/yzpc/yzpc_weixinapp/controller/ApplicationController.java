package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Application;
import com.yzpc.yzpc_weixinapp.entity.enums.ApplicationTypes;
import com.yzpc.yzpc_weixinapp.service.ApplicationService;
import com.yzpc.yzpc_weixinapp.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wq
 * @description 证书申请方法
 * @date 2024/12/21 15:10:42
 */
@CrossOrigin
@RestController
@Slf4j
public class ApplicationController {

    @Resource
    public ApplicationService applicationService;

    @Resource
    public StudentService studentService;

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
        if(!Arrays.asList(0,1,2,3,4).contains(application.getStatus()))
            return Result.error("申请状态错误");
        Integer originStatus = applicationService.getApplicationsByOwnId(application.getId()).getStatus();


        applicationService.changeApplication(application);

        //最终审核完成，添加分数同时预防重复添加
        if(!originStatus.equals(application.getStatus()) && application.getStatus()==4){
            String applicationType = application.getApplicationType();
            Integer score = application.getScore();
            Long studentId = application.getStudentId();
            int changes=0;
            switch (ApplicationTypes.fromValue(applicationType)){
                case SKILL:
                    changes=studentService.updateScore(studentId,"skill_score",score);
                    break;
                case CHINESE:
                    changes=studentService.updateScore(studentId,"chinese_score",score);
                    break;
                case ENGLISH:
                    changes=studentService.updateScore(studentId,"english_score",score);
                    break;
                case COMPUTER:
                    changes=studentService.updateScore(studentId,"computer_score",score);
                    break;
                default:
                    log.info("添加分数种类出错");
            }
            int changes2=studentService.updateTotalScore(studentId);
            log.info("修改"+changes+"行分数和"+changes2+"行总分。");

        }

        return Result.success();
    }

    @DeleteMapping("/application/delete")
    public Result delete(@RequestParam(name = "id") Long id){
        int changes = applicationService.delete(id);

        return Result.success(String.format("删除%d个数据",changes));
    }

}
