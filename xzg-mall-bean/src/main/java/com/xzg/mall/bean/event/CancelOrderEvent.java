package com.xzg.mall.bean.event;

import com.xzg.mall.bean.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  取消订单的事件
 * @author hutao
 */
@Data
@AllArgsConstructor
public class CancelOrderEvent {

    private Order order;
}
