package com.yzpc.yzpc_weixinapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherLogin {

    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String token;

    /**
     * 教师职位
     */
    private String role;



   public static TeacherLogin getTeacherV0(Teacher teacher,String token){
       Integer id1 = teacher.getId();
       String name1 = teacher.getName();
       String username1 = teacher.getUsername();
       String role1 = teacher.getRole();

       return new TeacherLogin(id1,name1,username1,token,role1);
   }
}