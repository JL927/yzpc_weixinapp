package com.yzpc.yzpc_weixinapp.service;

import com.yzpc.yzpc_weixinapp.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wq
* @description 针对表【class】的数据库操作Service
* @createDate 2024-12-20 15:47:16
*/
@Service
public interface ClassService extends IService<Class> {

    void addClass(Class newClass);

    int deleteClass(Integer classId);
    int deleteClassByTeacherId(Integer teacherId);

    List<Class> findClassesByTeacherId(Integer id);

    List<Class> findClassesByMajorId(Integer id);
}
