package com.xzg.mall.security.dao;

import com.xzg.mall.security.model.AppConnect;
import org.apache.ibatis.annotations.Param;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface AppConnectMapper extends BaseMapper<AppConnect> {

	AppConnect getByBizUserId(@Param("bizUserId") String bizUserId, @Param("appId") Integer appId);

	AppConnect getByUserId(@Param("userId") String userId, @Param("appId") Integer appId);

    String getUserIdByUnionId(@Param("bizUnionId") String bizUnionId);
}