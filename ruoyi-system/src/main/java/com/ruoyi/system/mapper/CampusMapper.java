package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Campus;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-02
 */
public interface CampusMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param campusId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Campus selectCampusByCampusId(Long campusId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param campus 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Campus> selectCampusList(Campus campus);

    /**
     * 新增【请填写功能名称】
     * 
     * @param campus 【请填写功能名称】
     * @return 结果
     */
    public int insertCampus(Campus campus);

    /**
     * 修改【请填写功能名称】
     * 
     * @param campus 【请填写功能名称】
     * @return 结果
     */
    public int updateCampus(Campus campus);

    /**
     * 删除【请填写功能名称】
     * 
     * @param campusId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCampusByCampusId(Long campusId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param campusIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCampusByCampusIds(Long[] campusIds);
}
