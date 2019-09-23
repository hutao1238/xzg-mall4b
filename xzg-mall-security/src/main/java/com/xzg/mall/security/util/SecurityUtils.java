package com.xzg.mall.security.util;


import com.xzg.mall.security.exception.UnauthorizedExceptionBase;
import com.xzg.mall.security.service.XzgSysUser;
import com.xzg.mall.security.service.XzgUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SecurityUtils {
	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 */
	public XzgUser getUser() {
		Authentication authentication = getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof XzgUser) {
			return (XzgUser) principal;
		}
		throw new UnauthorizedExceptionBase("无法获取普通用户信息");
	}

	/**
	 * 获取系统用户
	 */
	public XzgSysUser getSysUser() {
		Authentication authentication = getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof XzgSysUser) {
			return (XzgSysUser) principal;
		}
		throw new UnauthorizedExceptionBase("无法获取系统用户信息");
	}
}
