/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.api.controller;

import com.xzg.mall.bean.app.param.SendSmsParam;
import com.xzg.mall.bean.enums.SmsType;
import com.xzg.mall.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.xzg.mall.service.SmsLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/p/sms")
@Api(tags="发送验证码接口")
public class SmsController {

	@Autowired
	private SmsLogService smsLogService;
    /**
     * 发送验证码接口
     */
    @PostMapping("/send")
    @ApiOperation(value="发送验证码", notes="用户的发送验证码")
    public ResponseEntity<Void> audit(@RequestBody SendSmsParam sendSmsParam) {
		String userId = SecurityUtils.getUser().getUserId();
		smsLogService.sendSms(SmsType.VALID, userId, sendSmsParam.getMobile(),Maps.newHashMap());
		
		return ResponseEntity.ok().build();
    }
}
