package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Param;

public interface TeacherPostMapper {

    /** 根据教师ID删除关联的职位关系 */
    int deleteTeacherPostByTeacherId(Long teacherId);

    /** 批量插入教师与职位关系 */
    int batchTeacherPost(@Param("teacherId") Long teacherId, @Param("postIds") Long[] postIds);

    /** 根据教师ID查询已关联的职位ID数组 */
    Long[] selectPostIdsByTeacherId(Long teacherId);

    String selectPostNamesByTeacherId(Long teacherId);

}
