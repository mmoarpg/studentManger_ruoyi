package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ClazzMapper;
import com.ruoyi.system.domain.Clazz;
import com.ruoyi.system.service.IClazzService;

/**
 * 班级管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-02
 */
@Service
public class ClazzServiceImpl implements IClazzService 
{
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 查询班级管理
     * 
     * @param clazzId 班级管理主键
//     * @return 班级管理
     */
    @Override
    public Clazz selectClazzByClazzId(Long clazzId)
    {
        return clazzMapper.selectClazzByClazzId(clazzId);
    }

    /**
     * 查询班级管理列表
     * 
     * @param clazz 班级管理
     * @return 班级管理
     */
    @Override
    public List<Clazz> selectClazzList(Clazz clazz)
    {
        return clazzMapper.selectClazzList(clazz);
    }

    /**
     * 新增班级管理
     * 
     * @param clazz 班级管理
     * @return 结果
     */
    @Override
    public int insertClazz(Clazz clazz)
    {
        clazz.setCreateTime(DateUtils.getNowDate());
        return clazzMapper.insertClazz(clazz);
    }

    /**
     * 修改班级管理
     * 
     * @param clazz 班级管理
     * @return 结果
     */
    @Override
    public int updateClazz(Clazz clazz)
    {
        return clazzMapper.updateClazz(clazz);
    }

    /**
     * 批量删除班级管理
     * 
     * @param clazzIds 需要删除的班级管理主键
     * @return 结果
     */
    @Override
    public int deleteClazzByClazzIds(Long[] clazzIds)
    {
        return clazzMapper.deleteClazzByClazzIds(clazzIds);
    }

    /**
     * 删除班级管理信息
     * 
     * @param clazzId 班级管理主键
     * @return 结果
     */
    @Override
    public int deleteClazzByClazzId(Long clazzId)
    {
        return clazzMapper.deleteClazzByClazzId(clazzId);
    }
}
