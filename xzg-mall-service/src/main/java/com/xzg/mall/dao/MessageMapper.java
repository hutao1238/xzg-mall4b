package com.xzg.mall.dao;

import com.xzg.mall.bean.model.Message;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MessageMapper extends BaseMapper<Message> {

	void deleteByIds(Long[] ids);
}