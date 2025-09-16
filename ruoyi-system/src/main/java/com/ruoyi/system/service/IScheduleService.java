package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Schedule;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 课程排课Service接口
 *
 * @author ruoyi
 * @date 2025-09-10
 */
public interface IScheduleService
{
    /**
     * 查询课程排课
     *
     * @param scheduleId 课程排课主键
     * @return 课程排课
     */
    public Schedule selectScheduleByScheduleId(Long scheduleId);

    /**
     * 查询课程排课列表
     *
     * @param schedule 课程排课
     * @return 课程排课集合
     */
    public List<Schedule> selectScheduleList(Schedule schedule);

    /**
     * 新增课程排课
     *
     * @param schedule 课程排课
     * @return 结果
     */
    public int insertSchedule(Schedule schedule);

    /**
     * 修改课程排课
     *
     * @param schedule 课程排课
     * @return 结果
     */
    public int updateSchedule(Schedule schedule);

    /**
     * 批量删除课程排课
     *
     * @param scheduleIds 需要删除的课程排课主键集合
     * @return 结果
     */
    public int deleteScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 删除课程排课信息
     *
     * @param scheduleId 课程排课主键
     * @return 结果
     */
    public int deleteScheduleByScheduleId(Long scheduleId);

    public int checkConflict(Schedule schedule);

    /**
     * 根据班级和周次查询课表
     *
     * @param clazzId 班级ID
     * @param weekNo  第几周
     * @return 课表列表
     */
    List<Schedule> getScheduleByClazzAndWeek(@Param("clazzId") Long clazzId,
                                          @Param("weekNo") Integer weekNo);
}
