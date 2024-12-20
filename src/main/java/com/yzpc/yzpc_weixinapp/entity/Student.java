package com.yzpc.yzpc_weixinapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    private String password;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 3L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Student other = (Student) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getMajorId() == null ? other.getMajorId() == null : this.getMajorId().equals(other.getMajorId()))
            && (this.getComputerScore() == null ? other.getComputerScore() == null : this.getComputerScore().equals(other.getComputerScore()))
            && (this.getSkillScore() == null ? other.getSkillScore() == null : this.getSkillScore().equals(other.getSkillScore()))
            && (this.getEnglishScore() == null ? other.getEnglishScore() == null : this.getEnglishScore().equals(other.getEnglishScore()))
            && (this.getChineseScore() == null ? other.getChineseScore() == null : this.getChineseScore().equals(other.getChineseScore()))
            && (this.getTotalScore() == null ? other.getTotalScore() == null : this.getTotalScore().equals(other.getTotalScore()))
            && (this.getSatisfied() == null ? other.getSatisfied() == null : this.getSatisfied().equals(other.getSatisfied()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getMajorId() == null) ? 0 : getMajorId().hashCode());
        result = prime * result + ((getComputerScore() == null) ? 0 : getComputerScore().hashCode());
        result = prime * result + ((getSkillScore() == null) ? 0 : getSkillScore().hashCode());
        result = prime * result + ((getEnglishScore() == null) ? 0 : getEnglishScore().hashCode());
        result = prime * result + ((getChineseScore() == null) ? 0 : getChineseScore().hashCode());
        result = prime * result + ((getTotalScore() == null) ? 0 : getTotalScore().hashCode());
        result = prime * result + ((getSatisfied() == null) ? 0 : getSatisfied().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", classId=").append(classId);
        sb.append(", majorId=").append(majorId);
        sb.append(", computerScore=").append(computerScore);
        sb.append(", skillScore=").append(skillScore);
        sb.append(", englishScore=").append(englishScore);
        sb.append(", chineseScore=").append(chineseScore);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", satisfied=").append(satisfied);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}