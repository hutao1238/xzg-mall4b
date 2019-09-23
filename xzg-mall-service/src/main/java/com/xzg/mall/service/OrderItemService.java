package com.xzg.mall.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.OrderItem;

/**
 *
 * @author lgh on 2018/09/15.
 */
public interface OrderItemService extends IService<OrderItem> {

	List<OrderItem> getOrderItemsByOrderNumber(String orderNumber);

}
