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
import com.ruoyi.system.domain.CoursePackage;
import com.ruoyi.system.service.ICoursePackageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程包管理Controller
 *
 * @author ruoyi
 * @date 2025-09-05
 */
@RestController
@RequestMapping("/system/package")
public class CoursePackageController extends BaseController
{
    @Autowired
    private ICoursePackageService coursePackageService;

    /**
     * 查询课程包管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:list')")
    @GetMapping("/list")
    public TableDataInfo list(CoursePackage coursePackage)
    {
        startPage();
        List<CoursePackage> list = coursePackageService.selectCoursePackageList(coursePackage);
        return getDataTable(list);
    }

    /**
     * 导出课程包管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:export')")
    @Log(title = "课程包管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CoursePackage coursePackage)
    {
        List<CoursePackage> list = coursePackageService.selectCoursePackageList(coursePackage);
        ExcelUtil<CoursePackage> util = new ExcelUtil<CoursePackage>(CoursePackage.class);
        util.exportExcel(response, list, "课程包管理数据");
    }

    /**
     * 获取课程包管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:package:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(coursePackageService.selectCoursePackageById(id));
    }

    /**
     * 新增课程包管理
     */
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @Log(title = "课程包管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CoursePackage coursePackage)
    {
        return toAjax(coursePackageService.insertCoursePackage(coursePackage));
    }

    /**
     * 修改课程包管理
     */
    @PreAuthorize("@ss.hasPermi('system:package:edit')")
    @Log(title = "课程包管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CoursePackage coursePackage)
    {
        return toAjax(coursePackageService.updateCoursePackage(coursePackage));
    }

    /**
     * 删除课程包管理
     */
    @PreAuthorize("@ss.hasPermi('system:package:remove')")
    @Log(title = "课程包管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(coursePackageService.deleteCoursePackageByIds(ids));
    }

    @GetMapping("/listByCourse/{courseId}")
    public AjaxResult listByCourse(@PathVariable Long courseId) {
        List<CoursePackage> list = coursePackageService.selectPackagesByCourseId(courseId);
        return AjaxResult.success(list);
    }

}
