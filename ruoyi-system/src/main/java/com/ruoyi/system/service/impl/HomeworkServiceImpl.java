package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HomeworkMapper;
import com.ruoyi.system.domain.Homework;
import com.ruoyi.system.service.IHomeworkService;

/**
 * 作业定义Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-15
 */
@Service
public class HomeworkServiceImpl implements IHomeworkService 
{
    @Autowired
    private HomeworkMapper homeworkMapper;

    /**
     * 查询作业定义
     * 
     * @param homeworkId 作业定义主键
     * @return 作业定义
     */
    @Override
    public Homework selectHomeworkByHomeworkId(Long homeworkId)
    {
        return homeworkMapper.selectHomeworkByHomeworkId(homeworkId);
    }

    /**
     * 查询作业定义列表
     * 
     * @param homework 作业定义
     * @return 作业定义
     */
    @Override
    public List<Homework> selectHomeworkList(Homework homework)
    {
        return homeworkMapper.selectHomeworkList(homework);
    }

    /**
     * 新增作业定义
     * 
     * @param homework 作业定义
     * @return 结果
     */
    @Override
    public int insertHomework(Homework homework)
    {
        homework.setCreateTime(DateUtils.getNowDate());
        return homeworkMapper.insertHomework(homework);
    }

    /**
     * 修改作业定义
     * 
     * @param homework 作业定义
     * @return 结果
     */
    @Override
    public int updateHomework(Homework homework)
    {
        homework.setUpdateTime(DateUtils.getNowDate());
        return homeworkMapper.updateHomework(homework);
    }

    /**
     * 批量删除作业定义
     * 
     * @param homeworkIds 需要删除的作业定义主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkByHomeworkIds(Long[] homeworkIds)
    {
        return homeworkMapper.deleteHomeworkByHomeworkIds(homeworkIds);
    }

    /**
     * 删除作业定义信息
     * 
     * @param homeworkId 作业定义主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkByHomeworkId(Long homeworkId)
    {
        return homeworkMapper.deleteHomeworkByHomeworkId(homeworkId);
    }
}
