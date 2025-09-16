package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CoursePackage;

/**
 * 课程包管理Service接口
 *
 * @author ruoyi
 * @date 2025-09-05
 */
public interface ICoursePackageService
{
    /**
     * 查询课程包管理
     *
     * @param id 课程包管理主键
     * @return 课程包管理
     */
    public CoursePackage selectCoursePackageById(Long id);

    /**
     * 查询课程包管理列表
     *
     * @param coursePackage 课程包管理
     * @return 课程包管理集合
     */
    public List<CoursePackage> selectCoursePackageList(CoursePackage coursePackage);

    /**
     * 新增课程包管理
     *
     * @param coursePackage 课程包管理
     * @return 结果
     */
    public int insertCoursePackage(CoursePackage coursePackage);

    /**
     * 修改课程包管理
     *
     * @param coursePackage 课程包管理
     * @return 结果
     */
    public int updateCoursePackage(CoursePackage coursePackage);

    /**
     * 批量删除课程包管理
     *
     * @param ids 需要删除的课程包管理主键集合
     * @return 结果
     */
    public int deleteCoursePackageByIds(Long[] ids);

    /**
     * 删除课程包管理信息
     *
     * @param id 课程包管理主键
     * @return 结果
     */
    public int deleteCoursePackageById(Long id);

    List<CoursePackage> selectPackagesByCourseId(Long courseId);

}
