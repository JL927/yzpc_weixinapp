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
@AllArgsConstructor
@NoArgsConstructor
public class StudentLogin{

    private Long id;

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
     * 
     */
    private Integer teacherId;

    /**
     * 
     */
    private Integer classId;

    /**
     * 
     */
    private Integer majorId;

    /**
     * 
     */
    private Integer computerScore;

    /**
     * 
     */
    private Integer skillScore;

    /**
     * 
     */
    private Integer englishScore;

    /**
     * 
     */
    private Integer chineseScore;

    /**
     * 
     */
    private Integer totalScore;

    /**
     * 
     */
    private Integer satisfied;

    public static StudentLogin getStudentV0(Student student,String token){
        Long id1 = student.getId();
        String name1 = student.getName();
        String username1 = student.getUsername();
        Integer teacherId1 = student.getTeacherId();
        Integer classId1 = student.getClassId();
        Integer majorId1 = student.getMajorId();
        Integer computerScore1 = student.getComputerScore();
        Integer skillScore1 = student.getSkillScore();
        Integer englishScore1 = student.getEnglishScore();
        Integer chineseScore1 = student.getChineseScore();
        Integer totalScore1 = student.getTotalScore();
        Integer satisfied1 = student.getSatisfied();

        return new StudentLogin(id1,name1,username1,token,teacherId1,classId1,majorId1,computerScore1,skillScore1,englishScore1,chineseScore1,totalScore1,satisfied1);

    }

}