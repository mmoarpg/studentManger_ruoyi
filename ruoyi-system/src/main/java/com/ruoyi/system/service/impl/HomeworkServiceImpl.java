package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.HomeworkPublish;
import com.ruoyi.system.mapper.HomeworkMapper;
import com.ruoyi.system.domain.Homework;
import com.ruoyi.system.service.IHomeworkService;

/**
 * 作业定义Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-16
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
    @Transactional
    public int insertHomework(Homework homework) {
        int rows = homeworkMapper.insertHomework(homework);
        insertHomeworkPublish(homework);
        return rows;
    }

    /**
     * 修改作业定义
     * 
     * @param homework 作业定义
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHomework(Homework homework) {
        homeworkMapper.deleteHomeworkPublishByHomeworkId(homework.getHomeworkId());
        insertHomeworkPublish(homework);
        return homeworkMapper.updateHomework(homework);
    }

    /**
     * 批量删除作业定义
     * 
     * @param homeworkIds 需要删除的作业定义主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHomeworkByHomeworkIds(Long[] homeworkIds)
    {
        homeworkMapper.deleteHomeworkPublishByHomeworkIds(homeworkIds);
        return homeworkMapper.deleteHomeworkByHomeworkIds(homeworkIds);
    }

    /**
     * 删除作业定义信息
     * 
     * @param homeworkId 作业定义主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHomeworkByHomeworkId(Long homeworkId)
    {
        homeworkMapper.deleteHomeworkPublishByHomeworkId(homeworkId);
        return homeworkMapper.deleteHomeworkByHomeworkId(homeworkId);
    }

    /**
     * 新增作业发布信息
     * 
     * @param homework 作业定义对象
     */
    private void insertHomeworkPublish(Homework homework) {
        List<HomeworkPublish> list = homework.getHomeworkPublishList();
        if (list != null && list.size() > 0) {
            for (HomeworkPublish p : list) {
                p.setHomeworkId(homework.getHomeworkId());
            }
            homeworkMapper.batchHomeworkPublish(list);
        }
    }
}
