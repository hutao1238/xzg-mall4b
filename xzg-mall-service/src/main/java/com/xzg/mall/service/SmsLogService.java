/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.enums.SmsType;
import com.xzg.mall.bean.model.SmsLog;

/**
 *
 * @author lgh on 2018/11/29.
 */
public interface SmsLogService extends IService<SmsLog> {

	public void sendSms(SmsType smsType,String userId,String mobile,Map<String,String> params);
	
	public boolean checkValidCode(String mobile, String code,SmsType smsType);
}
