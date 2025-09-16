package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.CoursePackageRelation;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 课程关系Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-08
 */
public interface CoursePackageRelationMapper 
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
     * 删除课程关系
     * 
     * @param id 课程关系主键
     * @return 结果
     */
    public int deleteCoursePackageRelationById(Long id);

    /**
     * 批量删除课程关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCoursePackageRelationByIds(Long[] ids);

    int deleteByCourseId(Long courseId);

    int batchInsert(@Param("list") List<CoursePackageRelation> list);

    /** 回显：查某课程已勾选的packageId列表 */
    List<Long> selectPackageIdsByCourseId(Long courseId);

    List<String> selectPackageNamesByCourseId(Long courseId);

    int batchCoursePackageRelation(@org.apache.ibatis.annotations.Param("course_id") Long courseId, @org.apache.ibatis.annotations.Param("packageIds") Long[] packageIds);

}

