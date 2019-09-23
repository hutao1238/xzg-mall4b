package com.xzg.mall.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.enums.SmsType;
import com.xzg.mall.bean.model.SmsLog;

/**
 *
 * @author hutao
 */
public interface SmsLogService extends IService<SmsLog> {

	public void sendSms(SmsType smsType,String userId,String mobile,Map<String,String> params);
	
	public boolean checkValidCode(String mobile, String code,SmsType smsType);
}
