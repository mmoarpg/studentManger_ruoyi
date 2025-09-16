package com.ruoyi.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业定义对象 homework
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public class Homework extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业定义ID */
    private Long homeworkId;

    /** 作业标题 */
    @Excel(name = "作业标题")
    private String title;

    /** 作业说明/要求 */
    @Excel(name = "作业说明/要求")
    private String content;

    /** 布置老师ID */
    @Excel(name = "布置老师ID")
    private Long creatorId;

    /** 作业发布信息 */
    private List<HomeworkPublish> homeworkPublishList;

    public void setHomeworkId(Long homeworkId) 
    {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() 
    {
        return homeworkId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }

    public List<HomeworkPublish> getHomeworkPublishList()
    {
        return homeworkPublishList;
    }

    public void setHomeworkPublishList(List<HomeworkPublish> homeworkPublishList)
    {
        this.homeworkPublishList = homeworkPublishList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("homeworkId", getHomeworkId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("homeworkPublishList", getHomeworkPublishList())
            .toString();
    }
}
