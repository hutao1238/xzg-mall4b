package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.Message;

/**
 *
 * @author hutao.
 */
public interface MessageService extends IService<Message> {

	void deleteByIds(Long[] ids);

}
