package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CampusMapper;
import com.ruoyi.system.domain.Campus;
import com.ruoyi.system.service.ICampusService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-02
 */
@Service
public class CampusServiceImpl implements ICampusService 
{
    @Autowired
    private CampusMapper campusMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param campusId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Campus selectCampusByCampusId(Long campusId)
    {
        return campusMapper.selectCampusByCampusId(campusId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param campus 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Campus> selectCampusList(Campus campus)
    {
        return campusMapper.selectCampusList(campus);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param campus 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCampus(Campus campus)
    {
        campus.setCreateTime(DateUtils.getNowDate());
        return campusMapper.insertCampus(campus);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param campus 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCampus(Campus campus)
    {
        return campusMapper.updateCampus(campus);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param campusIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCampusByCampusIds(Long[] campusIds)
    {
        return campusMapper.deleteCampusByCampusIds(campusIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param campusId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCampusByCampusId(Long campusId)
    {
        return campusMapper.deleteCampusByCampusId(campusId);
    }
}
