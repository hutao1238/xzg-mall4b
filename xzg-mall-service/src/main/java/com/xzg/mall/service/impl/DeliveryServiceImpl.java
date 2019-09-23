package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.Delivery;
import com.xzg.mall.dao.DeliveryMapper;

import com.xzg.mall.service.DeliveryService;

/**
 *
 * @author hutao on 2018/11/26.
 */
@Service
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;

}
