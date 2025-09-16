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
import com.ruoyi.system.domain.HomeworkPublish;
import com.ruoyi.system.service.IHomeworkPublishService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作业发布Controller
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
@RestController
@RequestMapping("/system/publish")
public class HomeworkPublishController extends BaseController
{
    @Autowired
    private IHomeworkPublishService homeworkPublishService;

    /**
     * 查询作业发布列表
     */
    @PreAuthorize("@ss.hasPermi('system:publish:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeworkPublish homeworkPublish)
    {
        startPage();
        List<HomeworkPublish> list = homeworkPublishService.selectHomeworkPublishList(homeworkPublish);
        return getDataTable(list);
    }

    /**
     * 导出作业发布列表
     */
    @PreAuthorize("@ss.hasPermi('system:publish:export')")
    @Log(title = "作业发布", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeworkPublish homeworkPublish)
    {
        List<HomeworkPublish> list = homeworkPublishService.selectHomeworkPublishList(homeworkPublish);
        ExcelUtil<HomeworkPublish> util = new ExcelUtil<HomeworkPublish>(HomeworkPublish.class);
        util.exportExcel(response, list, "作业发布数据");
    }

    /**
     * 获取作业发布详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:publish:query')")
    @GetMapping(value = "/{publishId}")
    public AjaxResult getInfo(@PathVariable("publishId") Long publishId)
    {
        return success(homeworkPublishService.selectHomeworkPublishByPublishId(publishId));
    }

    /**
     * 新增作业发布
     */
    @PreAuthorize("@ss.hasPermi('system:publish:add')")
    @Log(title = "作业发布", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeworkPublish homeworkPublish)
    {
        return toAjax(homeworkPublishService.insertHomeworkPublish(homeworkPublish));
    }

    /**
     * 修改作业发布
     */
    @PreAuthorize("@ss.hasPermi('system:publish:edit')")
    @Log(title = "作业发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeworkPublish homeworkPublish)
    {
        return toAjax(homeworkPublishService.updateHomeworkPublish(homeworkPublish));
    }

    /**
     * 删除作业发布
     */
    @PreAuthorize("@ss.hasPermi('system:publish:remove')")
    @Log(title = "作业发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{publishIds}")
    public AjaxResult remove(@PathVariable Long[] publishIds)
    {
        return toAjax(homeworkPublishService.deleteHomeworkPublishByPublishIds(publishIds));
    }
}
