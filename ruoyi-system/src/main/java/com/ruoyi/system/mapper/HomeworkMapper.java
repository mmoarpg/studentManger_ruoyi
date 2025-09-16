package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Homework;
import com.ruoyi.system.domain.HomeworkPublish;

/**
 * 作业定义Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public interface HomeworkMapper 
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
     * 删除作业定义
     * 
     * @param homeworkId 作业定义主键
     * @return 结果
     */
    public int deleteHomeworkByHomeworkId(Long homeworkId);

    /**
     * 批量删除作业定义
     * 
     * @param homeworkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeworkByHomeworkIds(Long[] homeworkIds);

    /**
     * 批量删除作业发布
     * 
     * @param homeworkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeworkPublishByHomeworkIds(Long[] homeworkIds);
    
    /**
     * 批量新增作业发布
     * 
     * @param homeworkPublishList 作业发布列表
     * @return 结果
     */
    public int batchHomeworkPublish(List<HomeworkPublish> homeworkPublishList);
    

    /**
     * 通过作业定义主键删除作业发布信息
     * 
     * @param homeworkId 作业定义ID
     * @return 结果
     */
    public int deleteHomeworkPublishByHomeworkId(Long homeworkId);
}
