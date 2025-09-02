package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Student;

/**
 * 学员管理Service接口
 * 
 * @author ruoyi
 * @date 2025-08-31
 */
public interface IStudentService 
{
    /**
     * 查询学员管理
     * 
     * @param id 学员管理主键
     * @return 学员管理
     */
    public Student selectStudentById(Long id);

    /**
     * 查询学员管理列表
     * 
     * @param student 学员管理
     * @return 学员管理集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学员管理
     * 
     * @param student 学员管理
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学员管理
     * 
     * @param student 学员管理
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学员管理
     * 
     * @param ids 需要删除的学员管理主键集合
     * @return 结果
     */
    public int deleteStudentByIds(Long[] ids);

    /**
     * 删除学员管理信息
     * 
     * @param id 学员管理主键
     * @return 结果
     */
    public int deleteStudentById(Long id);
}
