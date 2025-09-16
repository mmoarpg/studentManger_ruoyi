package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业发布对象 homework_publish
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public class HomeworkPublish extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业发布ID */
    private Long publishId;

    /** 作业定义ID */
    @Excel(name = "作业定义ID")
    private Long homeworkId;

    /** 班级ID */
    @Excel(name = "班级ID")
    private Long clazzId;

    /** 课次ID（可空） */
    @Excel(name = "课次ID", readConverterExp = "可=空")
    private Long clazzCourseId;

    /** 课程ID（冗余） */
    @Excel(name = "课程ID", readConverterExp = "冗=余")
    private Long courseId;

    /** 发布老师ID */
    @Excel(name = "发布老师ID")
    private Long teacherId;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    private String clazzName;   // 冗余展示

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private String teacherName;
    private String courseName;  // 冗余展示

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    private String homeworkTitle; // 冗余展示

    public void setPublishId(Long publishId) 
    {
        this.publishId = publishId;
    }

    public Long getPublishId() 
    {
        return publishId;
    }
    public void setHomeworkId(Long homeworkId) 
    {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() 
    {
        return homeworkId;
    }
    public void setClazzId(Long clazzId) 
    {
        this.clazzId = clazzId;
    }

    public Long getClazzId() 
    {
        return clazzId;
    }
    public void setClazzCourseId(Long clazzCourseId) 
    {
        this.clazzCourseId = clazzCourseId;
    }

    public Long getClazzCourseId() 
    {
        return clazzCourseId;
    }
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("publishId", getPublishId())
            .append("homeworkId", getHomeworkId())
            .append("clazzId", getClazzId())
            .append("clazzCourseId", getClazzCourseId())
            .append("courseId", getCourseId())
            .append("teacherId", getTeacherId())
            .append("deadline", getDeadline())
            .append("createTime", getCreateTime())
            .toString();
    }
}
