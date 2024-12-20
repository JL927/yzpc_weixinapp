package com.yzpc.yzpc_weixinapp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzpc.yzpc_weixinapp.entity.Application;
import com.yzpc.yzpc_weixinapp.service.ApplicationService;
import com.yzpc.yzpc_weixinapp.mapper.ApplicationMapper;
import org.springframework.stereotype.Service;

/**
* @author wq
* @description 针对表【application】的数据库操作Service实现
* @createDate 2024-12-20 17:00:11
*/
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application>
    implements ApplicationService{

}




