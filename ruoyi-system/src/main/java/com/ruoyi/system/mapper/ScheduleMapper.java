package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Schedule;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 课程排课Mapper接口
 *
 * @author ruoyi
 * @date 2025-09-10
 */
public interface ScheduleMapper
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
     * 删除课程排课
     *
     * @param scheduleId 课程排课主键
     * @return 结果
     */
    public int deleteScheduleByScheduleId(Long scheduleId);

    /**
     * 批量删除课程排课
     *
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScheduleByScheduleIds(Long[] scheduleIds);


    public int checkConflict(Schedule schedule);

    public List<Schedule> selectByClazzAndWeek(@Param("clazzId") Long clazzId,
                                          @Param("weekNo") Integer weekNo);
}
