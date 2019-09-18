/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.Message;
import com.xzg.mall.dao.MessageMapper;
import com.xzg.mall.service.MessageService;

/**
 *
 * @author lgh on 2018/10/15.
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

	@Override
	public void deleteByIds(Long[] ids) {
		messageMapper.deleteByIds(ids);
	}

}
