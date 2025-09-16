package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.mapper.CoursePackageRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CoursePackageMapper;
import com.ruoyi.system.domain.CoursePackage;
import com.ruoyi.system.service.ICoursePackageService;

/**
 * 课程包管理Service业务层处理
 *
 * @author ruoyi
 * @date 2025-09-05
 */
@Service
public class CoursePackageServiceImpl implements ICoursePackageService
{
    @Autowired
    private CoursePackageMapper coursePackageMapper;
    @Autowired
    private CoursePackageRelationMapper coursePackageRelationMapper;

    /**
     * 查询课程包管理
     *
     * @param id 课程包管理主键
     * @return 课程包管理
     */
    @Override
    public CoursePackage selectCoursePackageById(Long id)
    {
        return coursePackageMapper.selectCoursePackageById(id);
    }

    /**
     * 查询课程包管理列表
     *
     * @param coursePackage 课程包管理
     * @return 课程包管理
     */
    @Override
    public List<CoursePackage> selectCoursePackageList(CoursePackage coursePackage)
    {
        return coursePackageMapper.selectCoursePackageList(coursePackage);
    }

    /**
     * 新增课程包管理
     *
     * @param coursePackage 课程包管理
     * @return 结果
     */
    @Override
    public int insertCoursePackage(CoursePackage coursePackage)
    {
        coursePackage.setCreateTime(DateUtils.getNowDate());
        return coursePackageMapper.insertCoursePackage(coursePackage);
    }

    /**
     * 修改课程包管理
     *
     * @param coursePackage 课程包管理
     * @return 结果
     */
    @Override
    public int updateCoursePackage(CoursePackage coursePackage)
    {
        return coursePackageMapper.updateCoursePackage(coursePackage);
    }

    /**
     * 批量删除课程包管理
     *
     * @param ids 需要删除的课程包管理主键
     * @return 结果
     */
    @Override
    public int deleteCoursePackageByIds(Long[] ids)
    {
        return coursePackageMapper.deleteCoursePackageByIds(ids);
    }

    /**
     * 删除课程包管理信息
     *
     * @param id 课程包管理主键
     * @return 结果
     */
    @Override
    public int deleteCoursePackageById(Long id)
    {
        return coursePackageMapper.deleteCoursePackageById(id);
    }

    @Override
    public List<CoursePackage> selectPackagesByCourseId(Long courseId) {
        return coursePackageMapper.selectPackagesByCourseId(courseId);
    }

    private void insertCourseRelation(Teacher teacher)
    {
        Long[] postIds = teacher.getPostIds();
        if (postIds != null && postIds.length > 0) {
            coursePackageRelationMapper.batchCoursePackageRelation(teacher.getTeacherId(), postIds);
        }
    }
}
