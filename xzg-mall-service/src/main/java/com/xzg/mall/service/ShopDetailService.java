package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.ShopDetail;

/**
 *
 * @author lgh on 2018/08/29.
 */
public interface ShopDetailService extends IService<ShopDetail> {

	void updateShopDetail(ShopDetail shopDetail,ShopDetail dbShopDetail);

	void deleteShopDetailByShopId(Long id);

	/**
	 * 根据店铺id获取店铺信息
	 * @param shopId
	 * @return
	 */
	ShopDetail getShopDetailByShopId(Long shopId);

	void removeShopDetailCacheByShopId(Long shopId);
}
