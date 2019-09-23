package com.xzg.mall.mp.component;

import com.xzg.mall.common.annotation.RedisLock;
import com.xzg.mall.common.util.RedisUtil;
import com.xzg.mall.mp.config.bean.WxMp;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.enums.TicketType;
import org.springframework.stereotype.Component;

/**
 * 基于Redis的微信配置provider.
 *
 * 已加入分布式锁的实现
 *
 * @author LGH
 */
@Component
public class WxMpInRedisConfigStorage extends WxMpDefaultConfigImpl {

	private static final String ACCESS_TOKEN_KEY = "wxMp:access_token:";

	private String accessTokenKey;

	public WxMpInRedisConfigStorage (WxMp wxMp) {
		this.setAppId(wxMp.getAppid());
		this.setSecret(wxMp.getSecret());
		this.setToken(wxMp.getToken());
		this.setAesKey(wxMp.getAesKey());
	}

	/**
	 * 每个公众号生成独有的存储key.
	 */
	@Override
	public void setAppId(String appId) {
		super.setAppId(appId);
		this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
	}

	private String getTicketRedisKey(TicketType type) {
		return String.format("wx:ticket:key:%s:%s", this.appId, type.getCode());
	}

	@Override
	public String getAccessToken() {
		return RedisUtil.get(accessTokenKey);
	}

	@Override
	public boolean isAccessTokenExpired() {
		return !RedisUtil.hasKey(accessTokenKey);
	}

	@Override
	@RedisLock(lockName = "updateMpAccessToken")
	public void updateAccessToken(WxAccessToken accessToken) {
		updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
	}


	@Override
	@RedisLock(lockName = "updateMpAccessToken")
	public void updateAccessToken(String accessToken, int expiresInSeconds) {
		RedisUtil.set(accessTokenKey, accessToken, expiresInSeconds - 200);
	}

	@Override
	public void expireAccessToken() {
		RedisUtil.del(accessTokenKey);
	}

	@Override
	public String getTicket(TicketType type) {
		return RedisUtil.get(this.getTicketRedisKey(type));
	}

	@Override
	public boolean isTicketExpired(TicketType type) {
		return !RedisUtil.hasKey(this.getTicketRedisKey(type));
	}

	@Override
	@RedisLock(lockName = "updateMpJsapiTicket")
	public void updateTicket(TicketType type, String jsapiTicket, int expiresInSeconds) {
		RedisUtil.set(this.getTicketRedisKey(type), jsapiTicket, expiresInSeconds - 200);
	}

	@Override
	public void expireTicket(TicketType type) {
		RedisUtil.del(this.getTicketRedisKey(type));
	}

}
