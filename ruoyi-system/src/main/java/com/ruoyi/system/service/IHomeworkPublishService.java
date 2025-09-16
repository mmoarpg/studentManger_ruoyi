package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.HomeworkPublish;

/**
 * 作业发布Service接口
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public interface IHomeworkPublishService 
{
    /**
     * 查询作业发布
     * 
     * @param publishId 作业发布主键
     * @return 作业发布
     */
    public HomeworkPublish selectHomeworkPublishByPublishId(Long publishId);

    /**
     * 查询作业发布列表
     * 
     * @param homeworkPublish 作业发布
     * @return 作业发布集合
     */
    public List<HomeworkPublish> selectHomeworkPublishList(HomeworkPublish homeworkPublish);

    /**
     * 新增作业发布
     * 
     * @param homeworkPublish 作业发布
     * @return 结果
     */
    public int insertHomeworkPublish(HomeworkPublish homeworkPublish);

    /**
     * 修改作业发布
     * 
     * @param homeworkPublish 作业发布
     * @return 结果
     */
    public int updateHomeworkPublish(HomeworkPublish homeworkPublish);

    /**
     * 批量删除作业发布
     * 
     * @param publishIds 需要删除的作业发布主键集合
     * @return 结果
     */
    public int deleteHomeworkPublishByPublishIds(Long[] publishIds);

    /**
     * 删除作业发布信息
     * 
     * @param publishId 作业发布主键
     * @return 结果
     */
    public int deleteHomeworkPublishByPublishId(Long publishId);
}
