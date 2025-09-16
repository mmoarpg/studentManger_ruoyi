package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程排课对象 schedule
 *
 * @author ruoyi
 * @date 2025-09-10
 */
public class Schedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排课ID */
    private Long scheduleId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 班级ID */
    @Excel(name = "班级ID")
    private Long clazzId;

    /** 周几（1-7） */
    @Excel(name = "周几", readConverterExp = "1=-7")
    private Long weekDay;

    /** 上课开始时间 */
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @Excel(name = "上课开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 上课结束时间 */
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @Excel(name = "上课结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 排课方式：0=手动，1=自动 */
    @Excel(name = "排课方式：0=手动，1=自动")
    private Long autoFlag;

    /** 课程名称 */
    private String courseName;

    /** 教师名称 */
    private String teacherName;

    /** 班级名称 */
    private String clazzName;

    /** 课程包名称 */
    private String packageName;

    private Integer startWeek;

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    private Integer packageId;

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }

    private Integer endWeek;

    public Integer getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(Integer weekNo) {
        this.weekNo = weekNo;
    }

    private Integer weekNo;

    public String getCourseName() {
        return courseName;
    }



    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    private String campusName;

    public void setScheduleId(Long scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId()
    {
        return scheduleId;
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

    public void setClazzId(Long clazzId)
    {
        this.clazzId = clazzId;
    }

    public Long getClazzId()
    {
        return clazzId;
    }

    public void setWeekDay(Long weekDay)
    {
        this.weekDay = weekDay;
    }

    public Long getWeekDay()
    {
        return weekDay;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setAutoFlag(Long autoFlag)
    {
        this.autoFlag = autoFlag;
    }

    public Long getAutoFlag()
    {
        return autoFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("scheduleId", getScheduleId())
                .append("courseId", getCourseId())
                .append("teacherId", getTeacherId())
                .append("clazzId", getClazzId())
                .append("weekDay", getWeekDay())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("autoFlag", getAutoFlag())
                .append("createTime", getCreateTime())
                .toString();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }
}
