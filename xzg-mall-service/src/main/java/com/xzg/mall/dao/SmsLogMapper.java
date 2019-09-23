package com.xzg.mall.dao;

import org.apache.ibatis.annotations.Param;

import com.xzg.mall.bean.model.SmsLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface SmsLogMapper extends BaseMapper<SmsLog> {

	void invalidSmsByMobileAndType(@Param("mobile") String mobile, @Param("type") Integer type);
}