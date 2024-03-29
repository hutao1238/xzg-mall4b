package com.xzg.mall.bean.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hutao
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShopCartItemDto extends ProductItemDto implements Serializable {
    private static final long serialVersionUID = -8284981156242930909L;

    @ApiModelProperty(value = "购物车ID", required = true)
    private Long basketId;

    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @ApiModelProperty(value = "规格名称", required = true)
    private String skuName;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "商品原价", required = true)
    private Double oriPrice;

    @ApiModelProperty(value = "推广员使用的推销卡号")
    private String distributionCardNo;

    @ApiModelProperty(value = "加入购物车的时间")
    private Date basketDate;
}
