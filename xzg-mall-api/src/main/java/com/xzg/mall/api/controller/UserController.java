package com.xzg.mall.api.controller;

import cn.hutool.core.util.StrUtil;
import com.xzg.mall.bean.app.dto.UserDto;
import com.xzg.mall.bean.app.param.UserInfoParam;
import com.xzg.mall.bean.model.User;
import com.xzg.mall.common.util.CacheManagerUtil;
import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xzg.mall.service.UserService;

import cn.hutool.extra.emoji.EmojiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;

@RestController
@RequestMapping("/p/user")
@Api(tags="用户接口")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
    
	private final MapperFacade mapperFacade;

	private final CacheManagerUtil cacheManagerUtil;
    /**
     * 查看用户接口
     */
    @GetMapping("/userInfo")
    @ApiOperation(value="查看用户信息", notes="根据用户ID（userId）获取用户信息")
    public ResponseEntity<UserDto> userInfo() {
    	String userId = SecurityUtils.getUser().getUserId();
    	User user = userService.getById(userId);
    	UserDto userDto = mapperFacade.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }
    
    @PutMapping("/setUserInfo")
    @ApiOperation(value="设置用户信息", notes="设置用户信息")
    public ResponseEntity<Void> setUserInfo(@RequestBody UserInfoParam userInfoParam) {
    	String userId = SecurityUtils.getUser().getUserId();
    	User user = new User();
    	user.setUserId(userId);
    	user.setPic(userInfoParam.getAvatarUrl());
    	user.setNickName(EmojiUtil.toAlias(userInfoParam.getNickName()));
    	userService.updateById(user);
		String cacheKey = App.MINI.value() + StrUtil.COLON + SecurityUtils.getUser().getBizUserId();
		cacheManagerUtil.evictCache("xzg_user", cacheKey);
        return ResponseEntity.ok(null);
    }
}
