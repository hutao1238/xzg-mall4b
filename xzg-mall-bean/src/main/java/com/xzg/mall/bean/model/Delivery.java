package com.xzg.mall.bean.model;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("tz_delivery")
public class Delivery implements Serializable {
    /**
     * ID
     */
    @TableId

    private Long dvyId;

    /**
     * 物流公司名称
     */

    private String dvyName;

    /**
     * 公司主页
     */

    private String companyHomeUrl;

    /**
     * 建立时间
     */

    private Date recTime;

    /**
     * 修改时间
     */

    private Date modifyTime;

    /**
     * 物流查询接口
     */

    private String queryUrl;
}