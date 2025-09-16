package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Course;
import com.ruoyi.system.domain.CoursePackageRelation;

/**
 * 课程关系Service接口
 * 
 * @author ruoyi
 * @date 2025-09-08
 */
public interface ICoursePackageRelationService 
{
    /**
     * 查询课程关系
     * 
     * @param id 课程关系主键
     * @return 课程关系
     */
    public CoursePackageRelation selectCoursePackageRelationById(Long id);

    /**
     * 查询课程关系列表
     * 
     * @param coursePackageRelation 课程关系
     * @return 课程关系集合
     */
    public List<CoursePackageRelation> selectCoursePackageRelationList(CoursePackageRelation coursePackageRelation);

    /**
     * 新增课程关系
     * 
     * @param coursePackageRelation 课程关系
     * @return 结果
     */
    public int insertCoursePackageRelation(CoursePackageRelation coursePackageRelation);

    /**
     * 修改课程关系
     * 
     * @param coursePackageRelation 课程关系
     * @return 结果
     */
    public int updateCoursePackageRelation(CoursePackageRelation coursePackageRelation);

    /**
     * 批量删除课程关系
     * 
     * @param ids 需要删除的课程关系主键集合
     * @return 结果
     */
    public int deleteCoursePackageRelationByIds(Long[] ids);

    /**
     * 删除课程关系信息
     * 
     * @param id 课程关系主键
     * @return 结果
     */
    public int deleteCoursePackageRelationById(Long id);
}
