package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级管理对象 clazz
 * 
 * @author ruoyi
 * @date 2025-09-02
 */
public class Clazz extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级ID */
    private Long clazzId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String clazzName;

    /** 年级 */
    @Excel(name = "年级")
    private String grade;

    /** 班主任ID */
    @Excel(name = "班主任ID")
    private Long teacherId;

    /** 班主任姓名 */
    @Excel(name = "班主任姓名")
    private String teacherName;

    /** 校区ID */
    @Excel(name = "校区ID")
    private Long campusId;

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    private String campusName;

    /** 状态：1-正常 0-禁用 */
    @Excel(name = "状态：1-正常 0-禁用")
    private Long status;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }



    public void setClazzId(Long clazzId) 
    {
        this.clazzId = clazzId;
    }

    public Long getClazzId() 
    {
        return clazzId;
    }

    public void setClazzName(String clazzName) 
    {
        this.clazzName = clazzName;
    }

    public String getClazzName() 
    {
        return clazzName;
    }

    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    public void setCampusId(Long campusId) 
    {
        this.campusId = campusId;
    }

    public Long getCampusId() 
    {
        return campusId;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clazzId", getClazzId())
            .append("clazzName", getClazzName())
            .append("grade", getGrade())
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("campusId", getCampusId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
