package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师管理对象 teacher
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师ID */
    private Long teacherId;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String teacherName;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    /** 职位id */
    private Long[] postIds;

    public String getPostNames() {
        return postNames;
    }

    public void setPostNames(String postNames) {
        this.postNames = postNames;
    }

    /** 职位 */
    private String postNames;

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("gender", getGender())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("createTime", getCreateTime())
            .toString();
    }
}
