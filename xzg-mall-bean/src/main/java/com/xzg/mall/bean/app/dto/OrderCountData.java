package com.xzg.mall.bean.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("我的订单数量")
public class OrderCountData {

    @ApiModelProperty(value = "所有订单数量")
    private Integer allCount;

    @ApiModelProperty(value = "待付款")
    private Integer unPay;

    @ApiModelProperty(value = "待发货")
    private Integer payed;

    @ApiModelProperty(value = "待收货")
    private Integer consignment;

    @ApiModelProperty(value = "待评价")
    private Integer confirm;

    @ApiModelProperty(value = "成功")
    private Integer success;

    @ApiModelProperty(value = "失败")
    private Integer close;


}
