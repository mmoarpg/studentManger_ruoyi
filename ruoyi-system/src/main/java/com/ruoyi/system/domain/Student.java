package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学员管理对象 student
 * 
 * @author ruoyi
 * @date 2025-08-31
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学员ID */
    private Long id;

    /** 学员姓名 */
    @Excel(name = "学员姓名")
    private String name;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 班级 */
    @Excel(name = "班级")
    private String bj;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }

    public void setBj(String bj) 
    {
        this.bj = bj;
    }

    public String getBj() 
    {
        return bj;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("age", getAge())
            .append("bj", getBj())
            .append("gender", getGender())
            .toString();
    }
}
