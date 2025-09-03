package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.service.IStudentService;

/**
 * 学生管理Service业务层处理
 *
 * @author ruoyi
 * @date 2025-09-03
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生管理
     *
     * @param studentId 学生管理主键
     * @return 学生管理
     */
    @Override
    public Student selectStudentByStudentId(Long studentId) {
        return studentMapper.selectStudentByStudentId(studentId);
    }

    /**
     * 查询学生管理列表
     *
     * @param student 学生管理
     * @return 学生管理
     */
    @Override
    public List<Student> selectStudentList(Student student) {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    @Override
    public int insertStudent(Student student) {
        student.setCreateTime(DateUtils.getNowDate());
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生管理
     *
     * @param studentIds 需要删除的学生管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStudentIds(Long[] studentIds) {
        return studentMapper.deleteStudentByStudentIds(studentIds);
    }

    /**
     * 删除学生管理信息
     *
     * @param studentId 学生管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStudentId(Long studentId) {
        return studentMapper.deleteStudentByStudentId(studentId);
    }

    @Override
    public String importStudents(List<Student> students, boolean updateSupport, String operName) {
        if (students == null || students.isEmpty()) {
            throw new ServiceException("导入数据不能为空");
        }
        int add = 0, upd = 0, fail = 0;
        StringBuilder sb = new StringBuilder();

        for (Student s : students) {
            try {
                // 这里以“手机号或邮箱”为唯一键示例，你也可以换成学号等
                Student exist = null;
                if (StringUtils.isNotEmpty(s.getPhone())) {
                    exist = studentMapper.selectStudentByStudentId(s.getStudentId());
                }

                s.setCreateBy(operName);
                if (exist == null) {
                    studentMapper.insertStudent(s);
                    add++;
                } else if (updateSupport) {
                    s.setStudentId(exist.getStudentId());
                    studentMapper.updateStudent(s);
                    upd++;
                } else {
                    fail++;
                    sb.append("\n已存在：").append(StringUtils.defaultString(s.getPhone(), s.getEmail()));
                }
            } catch (Exception e) {
                fail++;
                sb.append("\n失败：").append(s.getStudentName()).append(" -> ").append(e.getMessage());
            }
        }
        return "导入完成：新增 " + add + " 条，更新 " + upd + " 条，失败 " + fail + " 条"
                + (sb.length() > 0 ? ("\n详情：" + sb) : "");
    }
}
