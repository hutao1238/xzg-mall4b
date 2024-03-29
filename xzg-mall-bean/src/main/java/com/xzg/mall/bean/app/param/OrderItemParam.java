package com.xzg.mall.bean.app.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value= "购物车物品参数")
public class OrderItemParam {

	@NotNull(message = "产品ID不能为空")
	@ApiModelProperty(value = "产品ID",required=true)
	private Long prodId;
	
	@NotNull(message = "skuId不能为空")
	@ApiModelProperty(value = "skuId",required=true)
	private Long skuId;
	
	@NotNull(message = "产品数量不能为空")
	@Min(value = 1,message = "产品数量不能为空")
	@ApiModelProperty(value = "产品数量",required=true)
	private Integer prodCount;

	@NotNull(message = "店铺id不能为空")
	@ApiModelProperty(value = "店铺id",required=true)
	private Long shopId;

	@ApiModelProperty(value = "推广员使用的推销卡号")
	private String distributionCardNo;
}
