/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.bean.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tz_transcity")
public class Transcity implements Serializable {
    @TableId

    private Long transcityId;

    /**
     * 运费项id
     */

    private Long transfeeId;

    /**
     * 城市id
     */

    private Long cityId;

    /**
     * 城市名称
     */
    @TableField(exist=false)
    private String areaName;
}