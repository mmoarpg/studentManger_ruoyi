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
import com.ruoyi.system.domain.Homework;
import com.ruoyi.system.service.IHomeworkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作业定义Controller
 * 
 * @author ruoyi
 * @date 2025-09-15
 */
@RestController
@RequestMapping("/system/homework")
public class HomeworkController extends BaseController
{
    @Autowired
    private IHomeworkService homeworkService;

    /**
     * 查询作业定义列表
     */
    @PreAuthorize("@ss.hasPermi('system:homework:list')")
    @GetMapping("/list")
    public TableDataInfo list(Homework homework)
    {
        startPage();
        List<Homework> list = homeworkService.selectHomeworkList(homework);
        return getDataTable(list);
    }

    /**
     * 导出作业定义列表
     */
    @PreAuthorize("@ss.hasPermi('system:homework:export')")
    @Log(title = "作业定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Homework homework)
    {
        List<Homework> list = homeworkService.selectHomeworkList(homework);
        ExcelUtil<Homework> util = new ExcelUtil<Homework>(Homework.class);
        util.exportExcel(response, list, "作业定义数据");
    }

    /**
     * 获取作业定义详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:homework:query')")
    @GetMapping(value = "/{homeworkId}")
    public AjaxResult getInfo(@PathVariable("homeworkId") Long homeworkId)
    {
        return success(homeworkService.selectHomeworkByHomeworkId(homeworkId));
    }

    /**
     * 新增作业定义
     */
    @PreAuthorize("@ss.hasPermi('system:homework:add')")
    @Log(title = "作业定义", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Homework homework)
    {
        return toAjax(homeworkService.insertHomework(homework));
    }

    /**
     * 修改作业定义
     */
    @PreAuthorize("@ss.hasPermi('system:homework:edit')")
    @Log(title = "作业定义", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Homework homework)
    {
        return toAjax(homeworkService.updateHomework(homework));
    }

    /**
     * 删除作业定义
     */
    @PreAuthorize("@ss.hasPermi('system:homework:remove')")
    @Log(title = "作业定义", businessType = BusinessType.DELETE)
	@DeleteMapping("/{homeworkIds}")
    public AjaxResult remove(@PathVariable Long[] homeworkIds)
    {
        return toAjax(homeworkService.deleteHomeworkByHomeworkIds(homeworkIds));
    }
}
