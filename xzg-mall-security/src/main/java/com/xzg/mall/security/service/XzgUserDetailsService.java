package com.xzg.mall.security.service;

import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.exception.UsernameNotFoundExceptionBase;
import com.xzg.mall.security.model.AppConnect;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户详细信息
 *
 * @author hutao
 */
public interface XzgUserDetailsService extends UserDetailsService {

	/**
	 * 获取前端登陆的用户信息
	 *
	 * @param app 所登陆的应用
	 * @param bizUserId openId
	 * @return UserDetails
	 * @throws UsernameNotFoundExceptionBase
	 */
	XzgUser loadUserByAppIdAndBizUserId(App app, String bizUserId);

	/**
	 * 如果必要的话，插入新增用户
	 * @param appConnect
	 */
	void insertUserIfNecessary(AppConnect appConnect);
}
