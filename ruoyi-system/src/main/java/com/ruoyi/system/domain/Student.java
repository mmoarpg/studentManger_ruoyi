package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生管理对象 student
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学员ID */
    private Long studentId;

    /** 学员姓名 */
    @Excel(name = "学员姓名")
    private String studentName;

    /** 性别，男/女 */
    @Excel(name = "性别，男/女")
    private String gender;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 班级ID */
    @Excel(name = "班级ID")
    private Long classId;

    @Excel(name = "班级")
    private String clazzName;

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }


    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setClassId(Long classId)
    {
        this.classId = classId;
    }

    public Long getClassId()
    {
        return classId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("gender", getGender())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("classId", getClassId())
            .append("clazzName", getClazzName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
