package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.Student;
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
import com.ruoyi.system.domain.CoursePackageContent;
import com.ruoyi.system.service.ICoursePackageContentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程包内容管理Controller
 *
 * @author ruoyi
 * @date 2025-09-05
 */
@RestController
@RequestMapping("/system/content")
public class CoursePackageContentController extends BaseController
{
    @Autowired
    private ICoursePackageContentService coursePackageContentService;

    /**
     * 查询课程包内容管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(CoursePackageContent coursePackageContent)
    {
        startPage();
        List<CoursePackageContent> list = coursePackageContentService.selectCoursePackageContentList(coursePackageContent);
        return getDataTable(list);
    }

    /**
     * 导出课程包内容管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:export')")
    @Log(title = "课程包内容管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CoursePackageContent coursePackageContent)
    {
        List<CoursePackageContent> list = coursePackageContentService.selectCoursePackageContentList(coursePackageContent);
        ExcelUtil<CoursePackageContent> util = new ExcelUtil<CoursePackageContent>(CoursePackageContent.class);
        util.exportExcel(response, list, "课程包内容管理数据");
    }

    /**
     * 获取课程包内容管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(coursePackageContentService.selectCoursePackageContentById(id));
    }

    /**
     * 新增课程包内容管理
     */
    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @Log(title = "课程包内容管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CoursePackageContent coursePackageContent)
    {
        return toAjax(coursePackageContentService.insertCoursePackageContent(coursePackageContent));
    }

    /**
     * 修改课程包内容管理
     */
    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @Log(title = "课程包内容管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CoursePackageContent coursePackageContent)
    {
        return toAjax(coursePackageContentService.updateCoursePackageContent(coursePackageContent));
    }

    /**
     * 删除课程包内容管理
     */
    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @Log(title = "课程包内容管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(coursePackageContentService.deleteCoursePackageContentByIds(ids));
    }

    /**
     * 导入课程包内容
     */
    @Log(title = "课程包内容", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:content:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<CoursePackageContent> util = new ExcelUtil<>(CoursePackageContent.class);
        List<CoursePackageContent> contentList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = coursePackageContentService.importContent(contentList, updateSupport, operName);
        return success(message);
    }

    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('system:content:import')")
    @Log(title = "课程包内容管理", businessType = BusinessType.EXPORT)
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<CoursePackageContent> util = new ExcelUtil<>(CoursePackageContent.class);
        util.importTemplateExcel(response, "课程包内容导入模板");
    }

}
