package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Homework;

/**
 * 作业定义Service接口
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public interface IHomeworkService 
{
    /**
     * 查询作业定义
     * 
     * @param homeworkId 作业定义主键
     * @return 作业定义
     */
    public Homework selectHomeworkByHomeworkId(Long homeworkId);

    /**
     * 查询作业定义列表
     * 
     * @param homework 作业定义
     * @return 作业定义集合
     */
    public List<Homework> selectHomeworkList(Homework homework);

    /**
     * 新增作业定义
     * 
     * @param homework 作业定义
     * @return 结果
     */
    public int insertHomework(Homework homework);

    /**
     * 修改作业定义
     * 
     * @param homework 作业定义
     * @return 结果
     */
    public int updateHomework(Homework homework);

    /**
     * 批量删除作业定义
     * 
     * @param homeworkIds 需要删除的作业定义主键集合
     * @return 结果
     */
    public int deleteHomeworkByHomeworkIds(Long[] homeworkIds);

    /**
     * 删除作业定义信息
     * 
     * @param homeworkId 作业定义主键
     * @return 结果
     */
    public int deleteHomeworkByHomeworkId(Long homeworkId);
}
