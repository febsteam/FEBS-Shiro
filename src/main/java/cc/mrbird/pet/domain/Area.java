package cc.mrbird.pet.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 区域实体类
 * @author:shenshen
 * date:2018/10/17
 */
@Table(name = "t_area")
public class Area implements Serializable {

    private static final long serialVersionUID = -6422128262633708065L;
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "AREA_ID")
    @ExportConfig(value = "编号")
    private Long areaId;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "AREA_NAME")
    @ExportConfig(value = "区域名称")
    private String areaName;

    @Column(name = "CREATE_TIME")
    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.util.poi.convert.TimeConvert")
    private Date createTime;



    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
