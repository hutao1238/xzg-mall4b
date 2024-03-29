package com.xzg.mall.bean.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("购物车失效商品对象")
public class ShopCartExpiryItemDto {
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "商品项", required = true)
    private List<ShopCartItemDto> shopCartItemDtoList;

}
