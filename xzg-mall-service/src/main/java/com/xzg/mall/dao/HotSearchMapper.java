package com.xzg.mall.dao;

import com.xzg.mall.bean.dto.HotSearchDto;
import com.xzg.mall.bean.model.HotSearch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface HotSearchMapper extends BaseMapper<HotSearch> {
    List<HotSearchDto> getHotSearchDtoByShopId(Long shopId);
}