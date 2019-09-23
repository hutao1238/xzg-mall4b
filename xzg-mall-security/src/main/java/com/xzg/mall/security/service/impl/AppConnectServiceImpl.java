package com.xzg.mall.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzg.mall.security.service.AppConnectService;
import com.xzg.mall.security.dao.AppConnectMapper;
import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.model.AppConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



/**
 *
 * @author hutao on 2018/09/07.
 */
@Service
public class AppConnectServiceImpl extends ServiceImpl<AppConnectMapper, AppConnect> implements AppConnectService {

    @Autowired
    private AppConnectMapper appConnectMapper;

	/**
	 * XzgUserServiceImpl#insertUserIfNecessary 将会清楚该缓存信息
	 * @param bizUserId
	 * @param app
	 * @return
	 */
	@Override
	@Cacheable(cacheNames = "AppConnect", key = "#app.value() + ':' + #bizUserId")
	public AppConnect getByBizUserId(String bizUserId, App app) {
		return appConnectMapper.getByBizUserId(bizUserId, app.value());
	}

}
