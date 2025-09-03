package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Student;

/**
 * 学生管理Service接口
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
public interface IStudentService 
{
    /**
     * 查询学生管理
     * 
     * @param studentId 学生管理主键
     * @return 学生管理
     */
    public Student selectStudentByStudentId(Long studentId);

    /**
     * 查询学生管理列表
     * 
     * @param student 学生管理
     * @return 学生管理集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生管理
     * 
     * @param student 学生管理
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生管理
     * 
     * @param student 学生管理
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学生管理
     * 
     * @param studentIds 需要删除的学生管理主键集合
     * @return 结果
     */
    public int deleteStudentByStudentIds(Long[] studentIds);

    /**
     * 删除学生管理信息
     * 
     * @param studentId 学生管理主键
     * @return 结果
     */
    public int deleteStudentByStudentId(Long studentId);

    /**
     * 导入学生信息
     * @param students 学生信息列表
     * @param updateSupport 是否支持更新已存在的学生数据
     * @param operName 操作人员名称
     * @return 导入结果提示信息
     */
    String importStudents(List<Student> students, boolean updateSupport, String operName);
}
