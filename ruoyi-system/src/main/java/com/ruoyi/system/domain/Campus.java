package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 campus
 *
 * @author ruoyi
 * @date 2025-09-02
 */
public class Campus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 校区ID */
    private Long campusId;

    /** 校区名称 */
    @Excel(name = "校区名称")
    private String campusName;

    /** 校区地址 */
    @Excel(name = "校区地址")
    private String address;

    /** 校区联系电话 */
    @Excel(name = "校区联系电话")
    private String phone;

    public void setCampusId(Long campusId)
    {
        this.campusId = campusId;
    }

    public Long getCampusId()
    {
        return campusId;
    }

    public void setCampusName(String campusName)
    {
        this.campusName = campusName;
    }

    public String getCampusName()
    {
        return campusName;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("campusId", getCampusId())
            .append("campusName", getCampusName())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("createTime", getCreateTime())
            .toString();
    }
}
