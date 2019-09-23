package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.Message;

/**
 *
 * @author lgh on 2018/10/15.
 */
public interface MessageService extends IService<Message> {

	void deleteByIds(Long[] ids);

}
