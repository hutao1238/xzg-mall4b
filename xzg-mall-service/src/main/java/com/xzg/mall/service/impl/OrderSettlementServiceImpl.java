package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.OrderSettlement;
import com.xzg.mall.dao.OrderSettlementMapper;
import com.xzg.mall.service.OrderSettlementService;

/**
 *
 * @author hutao on 2018/11/10.
 */
@Service
public class OrderSettlementServiceImpl extends ServiceImpl<OrderSettlementMapper, OrderSettlement> implements OrderSettlementService {

    @Autowired
    private OrderSettlementMapper orderSettlementMapper;

	@Override
	public void updateSettlementsByPayNo(String outTradeNo, String transactionId) {
		orderSettlementMapper.updateSettlementsByPayNo(outTradeNo, transactionId);
	}

}
