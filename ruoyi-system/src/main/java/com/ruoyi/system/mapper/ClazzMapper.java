package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Clazz;

/**
 * 班级管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-02
 */
public interface ClazzMapper 
{
    /**
     * 查询班级管理
     * 
     * @param clazzId 班级管理主键
     * @return 班级管理
     */
    public Clazz selectClazzByClazzId(Long clazzId);

    /**
     * 查询班级管理列表
     * 
     * @param clazz 班级管理
     * @return 班级管理集合
     */
    public List<Clazz> selectClazzList(Clazz clazz);

    /**
     * 新增班级管理
     * 
     * @param clazz 班级管理
     * @return 结果
     */
    public int insertClazz(Clazz clazz);

    /**
     * 修改班级管理
     * 
     * @param clazz 班级管理
     * @return 结果
     */
    public int updateClazz(Clazz clazz);

    /**
     * 删除班级管理
     * 
     * @param clazzId 班级管理主键
     * @return 结果
     */
    public int deleteClazzByClazzId(Long clazzId);

    /**
     * 批量删除班级管理
     * 
     * @param clazzIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClazzByClazzIds(Long[] clazzIds);
}
