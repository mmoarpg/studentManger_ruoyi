package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.CoursePackageRelation;
import com.ruoyi.system.mapper.CoursePackageRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CourseMapper;
import com.ruoyi.system.domain.Course;
import com.ruoyi.system.service.ICourseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程管理Service业务层处理
 *
 * @author ruoyi
 * @date 2025-09-05
 */
@Service
public class CourseServiceImpl implements ICourseService
{
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CoursePackageRelationMapper relationMapper;

    /**
     * 查询课程管理
     *
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    @Override
    public Course selectCourseByCourseId(Long courseId)
    {
        return courseMapper.selectCourseByCourseId(courseId);
    }

    /**
     * 查询课程管理列表
     *
     * @param course 课程管理
     * @return 课程管理
     */
    @Override
    public List<Course> selectCourseList(Course course)
    {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增课程管理
     *
     * @param course 课程管理
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCourse(Course course) {
        // 1) 先保存课程主表
        int rows = courseMapper.insertCourse(course);
        // 2) 维护关系
        saveRelations(course.getCourseId(), course.getPackageIds());
        return rows;
    }

    /**
     * 修改课程管理
     *
     * @param course 课程管理
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCourse(Course course) {
        // 1) 更新课程主表
        int rows = courseMapper.updateCourse(course);
        // 2) 先删旧关系
        relationMapper.deleteByCourseId(course.getCourseId());
        // 3) 再保存新关系
        saveRelations(course.getCourseId(), course.getPackageIds());
        return rows;
    }


    /**
     * 批量删除课程管理
     *
     * @param courseIds 需要删除的课程管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseByCourseIds(Long[] courseIds)
    {
        return courseMapper.deleteCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程管理信息
     *
     * @param courseId 课程管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseByCourseId(Long courseId)
    {
        return courseMapper.deleteCourseByCourseId(courseId);
    }


    @Override
    public Course selectCourseById(Long courseId) {
        Course c = courseMapper.selectCourseById(courseId);
        // 回显勾选
        List<Long> pkgIds = relationMapper.selectPackageIdsByCourseId(courseId);
        c.setPackageIds(pkgIds);

        // 回显包名
        List<String> pkgNames = relationMapper.selectPackageNamesByCourseId(courseId);
        c.setPackageNames(pkgNames);
        return c;
    }


    /** 构造并批量入库 */
    private void saveRelations(Long courseId, List<Long> packageIds) {
        if (courseId == null || packageIds == null || packageIds.isEmpty()) return;
        List<CoursePackageRelation> list = packageIds.stream().distinct().map(pid -> {
            CoursePackageRelation r = new CoursePackageRelation();
            r.setCourseId(courseId);
            r.setPackageId(pid);
            return r;
        }).collect(Collectors.toList());
        relationMapper.batchInsert(list);
    }

}
