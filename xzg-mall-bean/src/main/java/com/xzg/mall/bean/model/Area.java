package com.xzg.mall.bean.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("tz_area")
public class Area implements Serializable {
    private static final long serialVersionUID = -6013320537436191451L;
    @TableId
    @ApiModelProperty(value = "地区id",required=true)
    private Long areaId;

    @ApiModelProperty(value = "地区名称",required=true)
    private String areaName;

    @ApiModelProperty(value = "地区上级id",required=true)
    private Long parentId;

    @ApiModelProperty(value = "地区层级",required=true)
    private Integer level;

    @TableField(exist=false)
    private List<Area> areas;
}
