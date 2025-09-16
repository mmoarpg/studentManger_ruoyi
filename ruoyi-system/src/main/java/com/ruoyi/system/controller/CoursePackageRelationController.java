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
import com.ruoyi.system.domain.CoursePackageRelation;
import com.ruoyi.system.service.ICoursePackageRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程关系Controller
 * 
 * @author ruoyi
 * @date 2025-09-08
 */
@RestController
@RequestMapping("/system/relation")
public class CoursePackageRelationController extends BaseController
{
    @Autowired
    private ICoursePackageRelationService coursePackageRelationService;

    /**
     * 查询课程关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(CoursePackageRelation coursePackageRelation)
    {
        startPage();
        List<CoursePackageRelation> list = coursePackageRelationService.selectCoursePackageRelationList(coursePackageRelation);
        return getDataTable(list);
    }

    /**
     * 导出课程关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "课程关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CoursePackageRelation coursePackageRelation)
    {
        List<CoursePackageRelation> list = coursePackageRelationService.selectCoursePackageRelationList(coursePackageRelation);
        ExcelUtil<CoursePackageRelation> util = new ExcelUtil<CoursePackageRelation>(CoursePackageRelation.class);
        util.exportExcel(response, list, "课程关系数据");
    }

    /**
     * 获取课程关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(coursePackageRelationService.selectCoursePackageRelationById(id));
    }

    /**
     * 新增课程关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "课程关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CoursePackageRelation coursePackageRelation)
    {
        return toAjax(coursePackageRelationService.insertCoursePackageRelation(coursePackageRelation));
    }

    /**
     * 修改课程关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "课程关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CoursePackageRelation coursePackageRelation)
    {
        return toAjax(coursePackageRelationService.updateCoursePackageRelation(coursePackageRelation));
    }

    /**
     * 删除课程关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "课程关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(coursePackageRelationService.deleteCoursePackageRelationByIds(ids));
    }
}
