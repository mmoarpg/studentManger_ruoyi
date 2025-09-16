package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CoursePackageContent;

/**
 * 课程包内容管理Mapper接口
 *
 * @author ruoyi
 * @date 2025-09-05
 */
public interface CoursePackageContentMapper
{
    /**
     * 查询课程包内容管理
     *
     * @param id 课程包内容管理主键
     * @return 课程包内容管理
     */
    public CoursePackageContent selectCoursePackageContentById(Long id);

    /**
     * 查询课程包内容管理列表
     *
     * @param coursePackageContent 课程包内容管理
     * @return 课程包内容管理集合
     */
    public List<CoursePackageContent> selectCoursePackageContentList(CoursePackageContent coursePackageContent);

    /**
     * 新增课程包内容管理
     *
     * @param coursePackageContent 课程包内容管理
     * @return 结果
     */
    public int insertCoursePackageContent(CoursePackageContent coursePackageContent);

    /**
     * 修改课程包内容管理
     *
     * @param coursePackageContent 课程包内容管理
     * @return 结果
     */
    public int updateCoursePackageContent(CoursePackageContent coursePackageContent);

    /**
     * 删除课程包内容管理
     *
     * @param id 课程包内容管理主键
     * @return 结果
     */
    public int deleteCoursePackageContentById(Long id);

    /**
     * 批量删除课程包内容管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCoursePackageContentByIds(Long[] ids);
}
