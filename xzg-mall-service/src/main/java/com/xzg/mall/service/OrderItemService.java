/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

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
