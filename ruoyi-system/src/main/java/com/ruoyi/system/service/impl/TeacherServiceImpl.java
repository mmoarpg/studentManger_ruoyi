package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.TeacherPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TeacherMapper;
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.service.ITeacherService;

/**
 * 教师管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@Service
public class TeacherServiceImpl implements ITeacherService 
{
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherPostMapper teacherPostMapper;

    /**
     * 查询教师管理
     * 
     * @param teacherId 教师管理主键
     * @return 教师管理
     */
    @Override
    public Teacher selectTeacherByTeacherId(Long teacherId)
    {
        Teacher teacher = teacherMapper.selectTeacherByTeacherId(teacherId);
        if (teacher != null) {
            // 查询关联职位ID数组，回显到前端
            Long[] postIds = teacherPostMapper.selectPostIdsByTeacherId(teacherId);
            teacher.setPostIds(postIds);
        }
        return teacher;
    }

    /**
     * 查询教师管理列表
     * 
     * @param teacher 教师管理
     * @return 教师管理
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        List<Teacher> list = teacherMapper.selectTeacherList(teacher);
        for (Teacher t : list) {
            String postNames = teacherPostMapper.selectPostNamesByTeacherId(t.getTeacherId());
            t.setPostNames(postNames);
        }
        return list;
    }

    /**
     * 新增教师管理
     * 
     * @param teacher 教师管理
     * @return 结果
     */
    @Override
    public int insertTeacher(Teacher teacher)
    {
        teacher.setCreateTime(DateUtils.getNowDate());
        int rows = teacherMapper.insertTeacher(teacher);
        insertTeacherPost(teacher); // 保存教师与职位关系
        return rows;
    }

    /**
     * 修改教师管理
     * 
     * @param teacher 教师管理
     * @return 结果
     */
    @Override
    public int updateTeacher(Teacher teacher)
    {
        int rows = teacherMapper.updateTeacher(teacher);
        // 先清空旧关系，再保存新关系
        teacherPostMapper.deleteTeacherPostByTeacherId(teacher.getTeacherId());
        insertTeacherPost(teacher);
        return rows;
    }

    /**
     * 批量删除教师管理
     * 
     * @param teacherIds 需要删除的教师管理主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByTeacherIds(Long[] teacherIds)
    {
        for (Long teacherId : teacherIds) {
            teacherPostMapper.deleteTeacherPostByTeacherId(teacherId); // 删除关系
        }
        return teacherMapper.deleteTeacherByTeacherIds(teacherIds);
    }

    /**
     * 删除教师管理信息
     * 
     * @param teacherId 教师管理主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByTeacherId(Long teacherId)
    {
        teacherPostMapper.deleteTeacherPostByTeacherId(teacherId); // 删除关系
        return teacherMapper.deleteTeacherByTeacherId(teacherId);
    }

    private void insertTeacherPost(Teacher teacher)
    {
        Long[] postIds = teacher.getPostIds();
        if (postIds != null && postIds.length > 0) {
            teacherPostMapper.batchTeacherPost(teacher.getTeacherId(), postIds);
        }
    }
}
