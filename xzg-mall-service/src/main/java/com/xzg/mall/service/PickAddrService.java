package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.PickAddr;

/**
 *
 * @author lgh on 2018/10/17.
 */
public interface PickAddrService extends IService<PickAddr> {

	void deleteByIds(Long[] ids);

}
