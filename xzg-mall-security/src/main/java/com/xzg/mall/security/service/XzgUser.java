package com.xzg.mall.security.service;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

/**
 * 用户详细信息
 */
@Getter
public class XzgUser extends User {

	/**
	 * 用户ID
	 */
	private String userId;

	private String bizUserId;

	@Setter
	private String pic;

	@Setter
	private String name;

	@Setter
	private boolean debugger;

	public XzgUser(String userId, String bizUserId, Integer appId, boolean enabled) {
		super(appId + StrUtil.COLON + bizUserId, "", enabled,true, true, true , Collections.emptyList());
		this.userId = userId;
		this.bizUserId = bizUserId;
	}
}
