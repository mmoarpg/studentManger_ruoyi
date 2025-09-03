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
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.service.IStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 学生管理Controller
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/student")
public class StudentController extends BaseController
{
    @Autowired
    private IStudentService studentService;

    /**
     * 查询学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 导出学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Student student) {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<>(Student.class);
        util.exportExcel(response, list, "学生管理数据");
    }

    /**
     * 导入学生数据
     */
    @PreAuthorize("@ss.hasPermi('system:student:import')")
    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(@RequestParam("file") MultipartFile file,
                                 @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        ExcelUtil<Student> util = new ExcelUtil<>(Student.class);
        List<Student> studentList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String msg = studentService.importStudents(studentList, updateSupport, operName);
        return AjaxResult.success(msg);
    }

    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('system:student:import')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<Student> util = new ExcelUtil<>(Student.class);
        util.importTemplateExcel(response, "学生导入模板");
    }


    /**
     * 获取学生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return success(studentService.selectStudentByStudentId(studentId));
    }

    /**
     * 新增学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Student student)
    {
        return toAjax(studentService.insertStudent(student));
    }

    /**
     * 修改学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Student student)
    {
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(studentService.deleteStudentByStudentIds(studentIds));
    }
}
