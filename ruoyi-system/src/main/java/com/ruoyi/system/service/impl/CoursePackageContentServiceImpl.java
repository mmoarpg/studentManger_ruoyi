package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CoursePackageContentMapper;
import com.ruoyi.system.domain.CoursePackageContent;
import com.ruoyi.system.service.ICoursePackageContentService;

/**
 * 课程包内容管理Service业务层处理
 *
 * @author ruoyi
 * @date 2025-09-05
 */
@Service
public class CoursePackageContentServiceImpl implements ICoursePackageContentService
{
    @Autowired
    private CoursePackageContentMapper coursePackageContentMapper;

    /**
     * 查询课程包内容管理
     *
     * @param id 课程包内容管理主键
     * @return 课程包内容管理
     */
    @Override
    public CoursePackageContent selectCoursePackageContentById(Long id)
    {
        return coursePackageContentMapper.selectCoursePackageContentById(id);
    }

    /**
     * 查询课程包内容管理列表
     *
     * @param coursePackageContent 课程包内容管理
     * @return 课程包内容管理
     */
    @Override
    public List<CoursePackageContent> selectCoursePackageContentList(CoursePackageContent coursePackageContent)
    {
        return coursePackageContentMapper.selectCoursePackageContentList(coursePackageContent);
    }

    /**
     * 新增课程包内容管理
     *
     * @param coursePackageContent 课程包内容管理
     * @return 结果
     */
    @Override
    public int insertCoursePackageContent(CoursePackageContent coursePackageContent)
    {
        coursePackageContent.setCreateTime(DateUtils.getNowDate());
        return coursePackageContentMapper.insertCoursePackageContent(coursePackageContent);
    }

    /**
     * 修改课程包内容管理
     *
     * @param coursePackageContent 课程包内容管理
     * @return 结果
     */
    @Override
    public int updateCoursePackageContent(CoursePackageContent coursePackageContent)
    {
        return coursePackageContentMapper.updateCoursePackageContent(coursePackageContent);
    }

    /**
     * 批量删除课程包内容管理
     *
     * @param ids 需要删除的课程包内容管理主键
     * @return 结果
     */
    @Override
    public int deleteCoursePackageContentByIds(Long[] ids)
    {
        return coursePackageContentMapper.deleteCoursePackageContentByIds(ids);
    }

    /**
     * 删除课程包内容管理信息
     *
     * @param id 课程包内容管理主键
     * @return 结果
     */
    @Override
    public int deleteCoursePackageContentById(Long id)
    {
        return coursePackageContentMapper.deleteCoursePackageContentById(id);
    }

    @Override
    public String importContent(List<CoursePackageContent> list, boolean updateSupport, String operName) {
        if (list == null || list.isEmpty()) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (CoursePackageContent content : list) {
            try {
                // 判断是否已存在（比如根据 packageId + contentName）
                CoursePackageContent exist = coursePackageContentMapper.selectCoursePackageContentById(content.getPackageId());
                if (exist == null) {
                    content.setCreateBy(operName);
                    coursePackageContentMapper.insertCoursePackageContent(content);
                    successNum++;
                } else if (updateSupport) {
                    content.setUpdateBy(operName);
                    coursePackageContentMapper.updateCoursePackageContent(content);
                    successNum++;
                } else {
                    failureNum++;
                    failureMsg.append("<br/>").append(content.getContentName()).append(" 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                failureMsg.append("<br/>").append(content.getContentName()).append(" 导入失败：").append(e.getMessage());
            }
        }

        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据有问题：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜，全部导入成功！共 " + successNum + " 条。");
        }
        return successMsg.toString();
    }

}
