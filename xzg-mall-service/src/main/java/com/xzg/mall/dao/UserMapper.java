package com.xzg.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzg.mall.bean.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

	User getUserByBizUserId(@Param("appId")Integer appId, @Param("bizUserId")String bizUserId);
}
