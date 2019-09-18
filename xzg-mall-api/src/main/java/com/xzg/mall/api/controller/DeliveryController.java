/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.api.controller;

import com.xzg.mall.bean.app.dto.DeliveryDto;
import com.xzg.mall.bean.model.Delivery;
import com.xzg.mall.bean.model.Order;
import com.xzg.mall.common.util.Json;
import com.xzg.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xzg.mall.service.DeliveryService;

import cn.hutool.http.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/delivery")
@Api(tags="查看物流接口")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private OrderService orderService;

    /**
     * 查看物流接口
     */
    @GetMapping("/check")
    @ApiOperation(value="查看物流", notes="根据订单号查看物流")
    @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String")
    public ResponseEntity<DeliveryDto> checkDelivery(String orderNumber) {

    	Order order = orderService.getOrderByOrderNumber(orderNumber);
    	Delivery delivery = deliveryService.getById(order.getDvyId());
    	String url = delivery.getQueryUrl().replace("{dvyFlowId}", order.getDvyFlowId());
    	String deliveryJson = HttpUtil.get(url);

    	DeliveryDto deliveryDto = Json.parseObject(deliveryJson, DeliveryDto.class);
    	deliveryDto.setDvyFlowId(order.getDvyFlowId());
    	deliveryDto.setCompanyHomeUrl(delivery.getCompanyHomeUrl());
    	deliveryDto.setCompanyName(delivery.getDvyName());
        return ResponseEntity.ok(deliveryDto);
    }
}
