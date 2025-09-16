package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 课程包管理对象 course_package
 *
 * @author ruoyi
 * @date 2025-09-05
 */
public class CoursePackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程包id */
    private Long id;

    /** 课程包 */
    @Excel(name = "课程包")
    private String packageName;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    private List<CoursePackageContent> children;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("packageName", getPackageName())
                .append("description", getDescription())
                .append("createTime", getCreateTime())
                .toString();
    }

    public class CoursePackageTree {
        private Long id;
        private String packageName;
        private List<CoursePackageContent> children; // 子节点：课程包内容
    }
}
