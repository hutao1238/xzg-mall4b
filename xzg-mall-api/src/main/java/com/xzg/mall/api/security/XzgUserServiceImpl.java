package com.xzg.mall.api.security;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import com.xzg.mall.bean.model.User;
import com.xzg.mall.common.annotation.RedisLock;
import com.xzg.mall.common.util.CacheManagerUtil;
import com.xzg.mall.dao.UserMapper;
import com.xzg.mall.security.dao.AppConnectMapper;
import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.exception.UsernameNotFoundExceptionBase;
import com.xzg.mall.security.model.AppConnect;
import com.xzg.mall.security.service.XzgUser;
import com.xzg.mall.security.service.XzgUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户详细信息
 *
 * @author hutao
 */
@Slf4j
@Service
@AllArgsConstructor
public class XzgUserServiceImpl implements XzgUserDetailsService {

	private final UserMapper userMapper;

	private final AppConnectMapper appConnectMapper;

	private final CacheManagerUtil cacheManagerUtil;


	@Override
	@SneakyThrows
	public XzgUser loadUserByUsername(String username) {
		if (StrUtil.isBlank(username) || !username.contains(StrUtil.COLON) ) {
			throw new UsernameNotFoundExceptionBase("无法获取用户信息");
		}
		String[] splitInfo = username.split(StrUtil.COLON);
		App app = App.instance(Integer.valueOf(splitInfo[0]));
		String bizUserId = splitInfo[1];
		return loadUserByAppIdAndBizUserId(app,bizUserId);
	}

	/**
	 * 获取前端登陆的用户信息
	 *
	 * @param app
	 * @param bizUserId openId
	 * @return UserDetails
	 * @throws UsernameNotFoundExceptionBase
	 */
	@Override
	public XzgUser loadUserByAppIdAndBizUserId(App app, String bizUserId) {

		String cacheKey = app.value() + StrUtil.COLON + bizUserId;

		XzgUser xzgUser = cacheManagerUtil.getCache("xzg_user", cacheKey);
		if (xzgUser != null) {
			return xzgUser;
		}


		User user = userMapper.getUserByBizUserId(app.value(), bizUserId);
		if (user == null) {
			throw new UsernameNotFoundExceptionBase("无法获取用户信息");
		}
		String name = StrUtil.isBlank(user.getRealName()) ? user.getNickName() : user.getRealName();
		xzgUser = new XzgUser(user.getUserId(), bizUserId, app.value(), user.getStatus() == 1);
		xzgUser.setName(name);
		xzgUser.setPic(user.getPic());

		cacheManagerUtil.putCache("xzg_sys_user",cacheKey, xzgUser);
		return xzgUser;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@RedisLock(lockName = "insertUser", key = "#appConnect.appId + ':' + #appConnect.bizUserId")
	@Caching(evict = {
			@CacheEvict(cacheNames = "xzg_user", key = "#appConnect.appId + ':' + #appConnect.bizUserId"),
			@CacheEvict(cacheNames = "AppConnect", key = "#appConnect.appId + ':' + #appConnect.bizUserId")
	})
	public void insertUserIfNecessary(AppConnect appConnect) {
		// 进入锁后再重新判断一遍用户是否创建
		AppConnect dbAppConnect = appConnectMapper.getByBizUserId(appConnect.getBizUserId(), appConnect.getAppId());
		if(dbAppConnect != null) {
			return;
		}

		String bizUnionId = appConnect.getBizUnionid();
		String userId = null;
		User user;

		if (StrUtil.isNotBlank(bizUnionId)) {
			userId = appConnectMapper.getUserIdByUnionId(bizUnionId);
		}
		if (StrUtil.isBlank(userId)) {
			userId = IdUtil.simpleUUID();
			Date now = new Date();
			user = new User();
			user.setUserId(userId);
			user.setModifyTime(now);
			user.setUserRegtime(now);
			user.setStatus(1);
			user.setNickName(EmojiUtil.toAlias(StrUtil.isBlank(appConnect.getNickName()) ? "" : appConnect.getNickName()));
			user.setPic(appConnect.getImageUrl());
			userMapper.insert(user);
		} else {
			user = userMapper.selectById(userId);
		}

		appConnect.setUserId(user.getUserId());

		appConnectMapper.insert(appConnect);
	}
}
