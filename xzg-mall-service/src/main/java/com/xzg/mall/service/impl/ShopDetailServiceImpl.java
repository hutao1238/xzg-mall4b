package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzg.mall.bean.model.ShopDetail;
import com.xzg.mall.dao.ShopDetailMapper;
import com.xzg.mall.service.AttachFileService;
import com.xzg.mall.service.ShopDetailService;

/**
 *
 * @author hutao on 2018/08/29.
 */
@Service
public class ShopDetailServiceImpl extends ServiceImpl<ShopDetailMapper, ShopDetail> implements ShopDetailService {

    @Autowired
    private ShopDetailMapper shopDetailMapper;
    
    @Autowired
    private AttachFileService attachFileService;

    @Override
    @Transactional(rollbackFor=Exception.class)
	@CacheEvict(cacheNames = "shop_detail", key = "#shopDetail.shopId")
    public void updateShopDetail(ShopDetail shopDetail,ShopDetail dbShopDetail) {
    	// 更新除数据库中的信息，再删除图片
    	shopDetailMapper.updateById(shopDetail);
//    	if (!Objects.equals(dbShopDetail.getShopLogo(), shopDetail.getShopLogo())) {
//    		// 删除logo
//    		attachFileService.deleteFile(shopDetail.getShopLogo());
//    	}
//    	// 店铺图片
//    	String shopPhotos = shopDetail.getShopPhotos();
//    	String[] shopPhotoArray =StrUtil.isBlank(shopPhotos)?new String[]{}: shopPhotos.split(",");
//
//    	// 数据库中的店铺图片
//    	String dbShopPhotos = dbShopDetail.getShopPhotos();
//    	String[] dbShopPhotoArray =StrUtil.isBlank(dbShopPhotos)?new String[]{}: dbShopPhotos.split(",");
//    	for (String dbShopPhoto : dbShopPhotoArray) {
//    		// 如果新插入的图片中没有旧数据中的图片，则删除旧数据中的图片
//			if (!ArrayUtil.contains(shopPhotoArray, dbShopPhoto)) {
//				attachFileService.deleteFile(dbShopPhoto);
//			}
//		}
    }
    
	@Override
	@Transactional(rollbackFor=Exception.class)
	@CacheEvict(cacheNames = "shop_detail", key = "#shopId")
	public void deleteShopDetailByShopId(Long shopId) {
		// 先删除数据库中的信息，再删除图片
		shopDetailMapper.deleteById(shopId);
		
//		ShopDetail shopDetail = shopDetailMapper.selectById(shopId);
		// 删除logo
//		attachFileService.deleteFile(shopDetail.getShopLogo());
//		// 删除店铺图片
//		String shopPhotos = shopDetail.getShopPhotos();
//		if (StrUtil.isNotBlank(shopPhotos)) {
//			String[] shopPhotoArray = shopPhotos.split(",");
//			for (String shopPhoto : shopPhotoArray) {
//				attachFileService.deleteFile(shopPhoto);
//			}
//		}
	}

	@Override
	@Cacheable(cacheNames = "shop_detail", key = "#shopId")
	public ShopDetail getShopDetailByShopId(Long shopId) {
		return shopDetailMapper.selectById(shopId);
	}

	@Override
	@CacheEvict(cacheNames = "shop_detail", key = "#shopId")
	public void removeShopDetailCacheByShopId(Long shopId) {
	}
}
