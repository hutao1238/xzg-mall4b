/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.bean.event;

import com.xzg.mall.bean.app.dto.ShopCartItemDto;
import com.xzg.mall.bean.app.dto.ShopCartOrderDto;
import com.xzg.mall.bean.app.param.OrderParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 确认订单时的事件
 * @author LGH
 */
@Data
@AllArgsConstructor
public class ConfirmOrderEvent {

    /**
     * 购物车已经组装好的店铺订单信息
     */
    private ShopCartOrderDto shopCartOrderDto;

    /**
     * 下单请求的参数
     */
    private OrderParam orderParam;

    /**
     * 店铺中的所有商品项
     */
    private List<ShopCartItemDto> shopCartItems;
}
