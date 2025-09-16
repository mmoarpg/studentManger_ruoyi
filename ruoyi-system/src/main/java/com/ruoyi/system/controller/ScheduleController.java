package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Schedule;
import com.ruoyi.system.service.IScheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程排课Controller
 * 
 * @author ruoyi
 * @date 2025-09-09
 */
@RestController
@RequestMapping("/system/schedule")
public class ScheduleController extends BaseController
{
    @Autowired
    private IScheduleService scheduleService;

    /**
     * 查询课程排课列表
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(Schedule schedule)
    {
        startPage();
        List<Schedule> list = scheduleService.selectScheduleList(schedule);
        return getDataTable(list);
    }

    /**
     * 导出课程排课列表
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:export')")
    @Log(title = "课程排课", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Schedule schedule)
    {
        List<Schedule> list = scheduleService.selectScheduleList(schedule);
        ExcelUtil<Schedule> util = new ExcelUtil<Schedule>(Schedule.class);
        util.exportExcel(response, list, "课程排课数据");
    }

    /**
     * 获取课程排课详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return success(scheduleService.selectScheduleByScheduleId(scheduleId));
    }

    /**
     * 新增课程排课
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:add')")
    @Log(title = "课程排课", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Schedule schedule)
    {
        return toAjax(scheduleService.insertSchedule(schedule));
    }

    /**
     * 修改课程排课
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:edit')")
    @Log(title = "课程排课", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Schedule schedule)
    {
        return toAjax(scheduleService.updateSchedule(schedule));
    }

    /**
     * 删除课程排课
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:remove')")
    @Log(title = "课程排课", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds)
    {
        return toAjax(scheduleService.deleteScheduleByScheduleIds(scheduleIds));
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody Schedule schedule) {
        int conflict = scheduleService.checkConflict(schedule);
        if (conflict > 0) {
            return AjaxResult.error("排课冲突：教师/班级/教室该时段已被占用");
        }
        return toAjax(scheduleService.updateSchedule(schedule));
    }

    // 班级 + 周次
    @GetMapping("/clazz/{clazzId}/{weekNo}")
    public AjaxResult getByClazzAndWeek(@PathVariable Long clazzId,
                                        @PathVariable Integer weekNo) {
        return AjaxResult.success(scheduleService.getScheduleByClazzAndWeek(clazzId, weekNo));
    }
}
