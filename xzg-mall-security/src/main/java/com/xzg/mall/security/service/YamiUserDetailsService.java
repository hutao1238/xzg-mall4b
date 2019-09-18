/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.security.service;

import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.exception.UsernameNotFoundExceptionBase;
import com.xzg.mall.security.model.AppConnect;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户详细信息
 *
 * @author
 */
public interface YamiUserDetailsService extends UserDetailsService {

	/**
	 * 获取前端登陆的用户信息
	 *
	 * @param app 所登陆的应用
	 * @param bizUserId openId
	 * @return UserDetails
	 * @throws UsernameNotFoundExceptionBase
	 */
	YamiUser loadUserByAppIdAndBizUserId(App app, String bizUserId);

	/**
	 * 如果必要的话，插入新增用户
	 * @param appConnect
	 */
	void insertUserIfNecessary(AppConnect appConnect);
}
