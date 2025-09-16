package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程包内容管理对象 course_package_content
 *
 * @author ruoyi
 * @date 2025-09-05
 */
public class CoursePackageContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程包内容id */
    private Long id;

    /** 课程包id */
    @Excel(name = "课程包id")
    private Long packageId;


    @Excel(name = "课程包名称")
    private String packageName;

    /** 课程包内容 */
    @Excel(name = "课程包内容")
    private String contentName;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setPackageId(Long packageId)
    {
        this.packageId = packageId;
    }

    public Long getPackageId()
    {
        return packageId;
    }

    public void setContentName(String contentName)
    {
        this.contentName = contentName;
    }

    public String getContentName()
    {
        return contentName;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("packageId", getPackageId())
                .append("contentName", getContentName())
                .append("description", getDescription())
                .append("createTime", getCreateTime())
                .toString();
    }
}
