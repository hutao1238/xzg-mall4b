package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.OrderSettlement;

/**
 *
 * @author hutao on 2018/11/10.
 */
public interface OrderSettlementService extends IService<OrderSettlement> {

	/**
	 * 根据内部订单号更新order settlement
	 */
	void updateSettlementsByPayNo(String outTradeNo, String transactionId);
}
