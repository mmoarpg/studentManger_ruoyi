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
import com.ruoyi.system.domain.Clazz;
import com.ruoyi.system.service.IClazzService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级管理Controller
 * 
 * @author ruoyi
 * @date 2025-09-02
 */
@RestController
@RequestMapping("/system/clazz")
public class ClazzController extends BaseController
{
    @Autowired
    private IClazzService clazzService;

    /**
     * 查询班级管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:list')")
    @GetMapping("/list")
    public TableDataInfo list(Clazz clazz)
    {
        startPage();
        List<Clazz> list = clazzService.selectClazzList(clazz);
        return getDataTable(list);
    }

    /**
     * 导出班级管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:export')")
    @Log(title = "班级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Clazz clazz)
    {
        List<Clazz> list = clazzService.selectClazzList(clazz);
        ExcelUtil<Clazz> util = new ExcelUtil<Clazz>(Clazz.class);
        util.exportExcel(response, list, "班级管理数据");
    }

    /**
     * 获取班级管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:query')")
    @GetMapping(value = "/{clazzId}")
    public AjaxResult getInfo(@PathVariable("clazzId") Long clazzId)
    {
        return success(clazzService.selectClazzByClazzId(clazzId));
    }

    /**
     * 新增班级管理
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:add')")
    @Log(title = "班级管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Clazz clazz)
    {
        return toAjax(clazzService.insertClazz(clazz));
    }

    /**
     * 修改班级管理
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:edit')")
    @Log(title = "班级管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Clazz clazz)
    {
        return toAjax(clazzService.updateClazz(clazz));
    }

    /**
     * 删除班级管理
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:remove')")
    @Log(title = "班级管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clazzIds}")
    public AjaxResult remove(@PathVariable Long[] clazzIds)
    {
        return toAjax(clazzService.deleteClazzByClazzIds(clazzIds));
    }
}
