/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.admin.task;

import java.util.Date;
import java.util.List;

import com.xzg.mall.service.OrderService;
import com.xzg.mall.service.ProductService;
import com.xzg.mall.service.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xzg.mall.bean.enums.OrderStatus;
import com.xzg.mall.bean.model.Order;
import com.xzg.mall.bean.model.OrderItem;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;


@Component("orderTask")
public class OrderTask {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SkuService skuService;

    public void cancelOrder(){
        Date now = new Date();
        logger.info("取消超时未支付订单。。。");
        // 获取30分钟之前未支付的订单
        List<Order> orders = orderService.listOrderAndOrderItems(OrderStatus.UNPAY.value(),DateUtil.offsetMinute(now, -30));
        if (CollectionUtil.isEmpty(orders)) {
            return;
        }
        orderService.cancelOrders(orders);
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                productService.removeProductCacheByProdId(orderItem.getProdId());
                skuService.removeSkuCacheBySkuId(orderItem.getSkuId(),orderItem.getProdId());
            }
        }
    }

    /**
     * 确认收货
     */
    public void confirmOrder(){
        Date now = new Date();
        logger.info("系统自动确认收货订单。。。");
        // 获取15天之前未支付的订单
        List<Order> orders = orderService.listOrderAndOrderItems(OrderStatus.CONSIGNMENT.value(),DateUtil.offsetDay(now, -15));
        if (CollectionUtil.isEmpty(orders)) {
            return;
        }
        orderService.confirmOrder(orders);
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                productService.removeProductCacheByProdId(orderItem.getProdId());
                skuService.removeSkuCacheBySkuId(orderItem.getSkuId(),orderItem.getProdId());
            }
        }
    }

}
