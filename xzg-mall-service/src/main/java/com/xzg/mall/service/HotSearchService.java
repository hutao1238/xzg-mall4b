package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.dto.HotSearchDto;
import com.xzg.mall.bean.model.HotSearch;

import java.util.List;

/**
 *
 * @author hutao
 */
public interface HotSearchService extends IService<HotSearch> {

    List<HotSearchDto> getHotSearchDtoByshopId(Long shopId);

    void removeHotSearchDtoCacheByshopId(Long shopId);
}
