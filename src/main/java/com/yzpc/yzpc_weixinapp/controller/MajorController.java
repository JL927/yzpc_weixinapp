package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Major;
import com.yzpc.yzpc_weixinapp.service.MajorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wq
 * @description 专业信息管理
 * @date 2024/12/30 15:09:47
 */
@RestController
public class MajorController {
    @Resource
    public MajorService majorService;

    @GetMapping("/major/getAll")
    public Result getAll(){
        List<Major> list = majorService.list();



        return Result.success(list);
    }
}
