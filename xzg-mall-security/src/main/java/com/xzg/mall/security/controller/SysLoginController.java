package com.xzg.mall.security.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.xzg.mall.security.util.SecurityUtils;
import com.xzg.mall.security.constants.SecurityConstants;

import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.xzg.mall.common.util.RedisUtil;
import com.xzg.mall.common.util.SimpleCaptcha;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 登录相关
 * @author hutao
 */
@Controller
@AllArgsConstructor
@ApiIgnore
public class SysLoginController {

	private final CacheManager cacheManager;


	@GetMapping("/captcha.jpg")
	public void login(HttpServletResponse response,String uuid) {
		//定义图形验证码的长、宽、验证码字符数、干扰元素个数
		SimpleCaptcha simpleCaptcha = new SimpleCaptcha(200, 50, 4, 20);
		try {
			simpleCaptcha.write(response.getOutputStream());
			RedisUtil.set(SecurityConstants.SPRING_SECURITY_RESTFUL_IMAGE_CODE+uuid, simpleCaptcha.getCode(), 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 退出
	 */
	@PostMapping(value = "/sys/logout")
	public ResponseEntity<String> logout() {
		Cache cache = cacheManager.getCache("xzg_sys_user");
		if (cache != null) {
			cache.evict(SecurityUtils.getSysUser().getUsername());
		}
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok().build();
	}

}
