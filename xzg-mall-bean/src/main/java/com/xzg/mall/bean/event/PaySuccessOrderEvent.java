package com.xzg.mall.bean.event;

import com.xzg.mall.bean.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 *  订单付款成功的事件
 * @author
 */
@Data
@AllArgsConstructor
public class PaySuccessOrderEvent {

    private List<Order> orders;
}
