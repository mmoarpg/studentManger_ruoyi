package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CoursePackageRelationMapper;
import com.ruoyi.system.domain.CoursePackageRelation;
import com.ruoyi.system.service.ICoursePackageRelationService;

/**
 * 课程关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-08
 */
@Service
public class CoursePackageRelationServiceImpl implements ICoursePackageRelationService 
{
    @Autowired
    private CoursePackageRelationMapper coursePackageRelationMapper;


    /**
     * 查询课程关系
     * 
     * @param id 课程关系主键
     * @return 课程关系
     */
    @Override
    public CoursePackageRelation selectCoursePackageRelationById(Long id)
    {
        return coursePackageRelationMapper.selectCoursePackageRelationById(id);
    }

    /**
     * 查询课程关系列表
     * 
     * @param coursePackageRelation 课程关系
     * @return 课程关系
     */
    @Override
    public List<CoursePackageRelation> selectCoursePackageRelationList(CoursePackageRelation coursePackageRelation)
    {
        return coursePackageRelationMapper.selectCoursePackageRelationList(coursePackageRelation);
    }

    /**
     * 新增课程关系
     * 
     * @param coursePackageRelation 课程关系
     * @return 结果
     */
    @Override
    public int insertCoursePackageRelation(CoursePackageRelation coursePackageRelation)
    {
        coursePackageRelation.setCreateTime(DateUtils.getNowDate());
        return coursePackageRelationMapper.insertCoursePackageRelation(coursePackageRelation);
    }

    /**
     * 修改课程关系
     * 
     * @param coursePackageRelation 课程关系
     * @return 结果
     */
    @Override
    public int updateCoursePackageRelation(CoursePackageRelation coursePackageRelation)
    {
        return coursePackageRelationMapper.updateCoursePackageRelation(coursePackageRelation);
    }

    /**
     * 批量删除课程关系
     * 
     * @param ids 需要删除的课程关系主键
     * @return 结果
     */
    @Override
    public int deleteCoursePackageRelationByIds(Long[] ids)
    {
        return coursePackageRelationMapper.deleteCoursePackageRelationByIds(ids);
    }

    /**
     * 删除课程关系信息
     * 
     * @param id 课程关系主键
     * @return 结果
     */
    @Override
    public int deleteCoursePackageRelationById(Long id)
    {
        return coursePackageRelationMapper.deleteCoursePackageRelationById(id);
    }
}
