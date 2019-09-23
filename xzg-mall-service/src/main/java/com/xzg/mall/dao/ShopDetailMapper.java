package com.xzg.mall.dao;

import com.xzg.mall.bean.model.ShopDetail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ShopDetailMapper extends BaseMapper<ShopDetail> {

    Integer getIsDistributionByShopId(Long shopId);
}