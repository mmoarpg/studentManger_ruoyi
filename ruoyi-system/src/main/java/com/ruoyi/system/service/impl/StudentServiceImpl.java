package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.service.IStudentService;

/**
 * 学员管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-31
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学员管理
     * 
     * @param id 学员管理主键
     * @return 学员管理
     */
    @Override
    public Student selectStudentById(Long id)
    {
        return studentMapper.selectStudentById(id);
    }

    /**
     * 查询学员管理列表
     * 
     * @param student 学员管理
     * @return 学员管理
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学员管理
     * 
     * @param student 学员管理
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学员管理
     * 
     * @param student 学员管理
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学员管理
     * 
     * @param ids 需要删除的学员管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentByIds(Long[] ids)
    {
        return studentMapper.deleteStudentByIds(ids);
    }

    /**
     * 删除学员管理信息
     * 
     * @param id 学员管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentById(Long id)
    {
        return studentMapper.deleteStudentById(id);
    }
}
