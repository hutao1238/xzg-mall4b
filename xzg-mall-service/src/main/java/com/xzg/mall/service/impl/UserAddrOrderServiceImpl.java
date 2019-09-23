package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzg.mall.dao.UserAddrOrderMapper;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.UserAddrOrder;
import com.xzg.mall.service.UserAddrOrderService;

@Service
public class UserAddrOrderServiceImpl extends ServiceImpl<UserAddrOrderMapper, UserAddrOrder> implements UserAddrOrderService{

}
