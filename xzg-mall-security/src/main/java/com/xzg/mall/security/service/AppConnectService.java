package com.xzg.mall.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.model.AppConnect;

/**
 *
 * @author hutao on 2018/09/07.
 */
public interface AppConnectService extends IService<AppConnect> {

	AppConnect getByBizUserId(String bizUserId, App app);
}
