package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.entity.Class;
import com.yzpc.yzpc_weixinapp.service.ClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wq
 * @description 班级方法
 * @date 2024/12/20 15:53:50
 */
@CrossOrigin
@RestController
public class ClassController {
    @Resource
    public ClassService classService;

    /**
     * 添加班级
     * @param name 班级名称
     * @param teacherId 教师id
     * @param majorId 专业id
     * @return
     */
    @PostMapping("/class/add") //  "/class/add?name=旅游一班&teacherId=1&majorId=1"
    public Result addClass(@RequestParam(name = "name") String name,
                           @RequestParam(name = "teacherId") Integer teacherId,
                           @RequestParam(name = "majorId") Integer majorId){

        Class newClass =new Class();
        newClass.setName(name);
        newClass.setTeacherId(teacherId);
        newClass.setMajorId(majorId);

        classService.addClass(newClass);
        return Result.success();

    }

    @DeleteMapping("/class/delete")
    public Result deleteClass(@RequestParam(name = "classId") Integer classId){
        int i = classService.deleteClass(classId);
        if(i>0)
            return Result.success();
        else
            return Result.error("fail to delete");
    }

    /**
     * 查询一个老师名下的所有班级，输入老师id
     * @param id 老师id
     * @return 所有班级
     */
    @GetMapping("/class/getByTeacherId")
    public Result getByTeacherId(@RequestParam(name = "teacherId") Integer id){
        List<Class> classes = classService.findClassesByTeacherId(id);
        return Result.success(classes);
    }

    @GetMapping("/class/getByMajorId")
    public Result getByMajorId(@RequestParam(name = "majorId") Integer id){
        List<Class> classes = classService.findClassesByMajorId(id);
        return Result.success(classes);
    }



}

