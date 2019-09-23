package com.xzg.mall.bean.event;

import com.xzg.mall.bean.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 确认收货的事件
 * @author hutao
 */
@Data
@AllArgsConstructor
public class ReceiptOrderEvent {

    private Order order;
}
