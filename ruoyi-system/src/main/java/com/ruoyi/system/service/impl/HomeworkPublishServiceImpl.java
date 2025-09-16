package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HomeworkPublishMapper;
import com.ruoyi.system.domain.HomeworkPublish;
import com.ruoyi.system.service.IHomeworkPublishService;

/**
 * 作业发布Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
@Service
public class HomeworkPublishServiceImpl implements IHomeworkPublishService 
{
    @Autowired
    private HomeworkPublishMapper homeworkPublishMapper;

    /**
     * 查询作业发布
     * 
     * @param publishId 作业发布主键
     * @return 作业发布
     */
    @Override
    public HomeworkPublish selectHomeworkPublishByPublishId(Long publishId)
    {
        return homeworkPublishMapper.selectHomeworkPublishByPublishId(publishId);
    }

    /**
     * 查询作业发布列表
     * 
     * @param homeworkPublish 作业发布
     * @return 作业发布
     */
    @Override
    public List<HomeworkPublish> selectHomeworkPublishList(HomeworkPublish homeworkPublish)
    {
        return homeworkPublishMapper.selectHomeworkPublishList(homeworkPublish);
    }

    /**
     * 新增作业发布
     * 
     * @param homeworkPublish 作业发布
     * @return 结果
     */
    @Override
    public int insertHomeworkPublish(HomeworkPublish homeworkPublish)
    {
        homeworkPublish.setCreateTime(DateUtils.getNowDate());
        return homeworkPublishMapper.insertHomeworkPublish(homeworkPublish);
    }

    /**
     * 修改作业发布
     * 
     * @param homeworkPublish 作业发布
     * @return 结果
     */
    @Override
    public int updateHomeworkPublish(HomeworkPublish homeworkPublish)
    {
        return homeworkPublishMapper.updateHomeworkPublish(homeworkPublish);
    }

    /**
     * 批量删除作业发布
     * 
     * @param publishIds 需要删除的作业发布主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkPublishByPublishIds(Long[] publishIds)
    {
        return homeworkPublishMapper.deleteHomeworkPublishByPublishIds(publishIds);
    }

    /**
     * 删除作业发布信息
     * 
     * @param publishId 作业发布主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkPublishByPublishId(Long publishId)
    {
        return homeworkPublishMapper.deleteHomeworkPublishByPublishId(publishId);
    }
}
