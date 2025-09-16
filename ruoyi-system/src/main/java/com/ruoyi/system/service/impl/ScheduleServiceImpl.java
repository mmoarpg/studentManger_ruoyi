package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.Schedule;
import com.ruoyi.system.mapper.ScheduleMapper;
import com.ruoyi.system.service.IScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 课程排课Service业务层处理
 *
 * @author ruoyi
 * @date 2025-09-10
 */
@Service
public class ScheduleServiceImpl implements IScheduleService
{
    @Resource
    private ScheduleMapper scheduleMapper;

    /**
     * 查询课程排课
     *
     * @param scheduleId 课程排课主键
     * @return 课程排课
     */
    @Override
    public Schedule selectScheduleByScheduleId(Long scheduleId)
    {
        return scheduleMapper.selectScheduleByScheduleId(scheduleId);
    }

    /**
     * 查询课程排课列表
     *
     * @param schedule 课程排课
     * @return 课程排课集合
     */
    @Override
    public List<Schedule> selectScheduleList(Schedule schedule)
    {
        return scheduleMapper.selectScheduleList(schedule);
    }

    /**
     * 新增课程排课（带冲突校验）
     *
     * @param schedule 课程排课
     * @return 结果
     */
    @Override
    public int insertSchedule(Schedule schedule)
    {
        if (scheduleMapper.checkConflict(schedule) > 0) {
            throw new ServiceException("该时间段已有课程安排，排课冲突！");
        }
        return scheduleMapper.insertSchedule(schedule);
    }

    /**
     * 修改课程排课（带冲突校验）
     *
     * @param schedule 课程排课
     * @return 结果
     */
    @Override
    public int updateSchedule(Schedule schedule)
    {
        if (scheduleMapper.checkConflict(schedule) > 0) {
            throw new ServiceException("该时间段已有课程安排，排课冲突！");
        }
        return scheduleMapper.updateSchedule(schedule);
    }

    /**
     * 批量删除课程排课
     *
     * @param scheduleIds 需要删除的课程排课主键集合
     * @return 结果
     */
    @Override
    public int deleteScheduleByScheduleIds(Long[] scheduleIds)
    {
        return scheduleMapper.deleteScheduleByScheduleIds(scheduleIds);
    }

    /**
     * 删除课程排课信息
     *
     * @param scheduleId 课程排课主键
     * @return 结果
     */
    @Override
    public int deleteScheduleByScheduleId(Long scheduleId)
    {
        return scheduleMapper.deleteScheduleByScheduleId(scheduleId);
    }

    /**
     * 冲突检测
     *
     * @param schedule 课程排课
     * @return 结果
     */
    @Override
    public int checkConflict(Schedule schedule)
    {
        return scheduleMapper.checkConflict(schedule);
    }

    @Override
    public List<Schedule> getScheduleByClazzAndWeek(Long clazzId, Integer weekNo) {
        return scheduleMapper.selectByClazzAndWeek(clazzId, weekNo);
    }
}
