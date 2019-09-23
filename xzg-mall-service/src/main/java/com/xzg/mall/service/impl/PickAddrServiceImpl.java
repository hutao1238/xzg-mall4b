package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.PickAddr;
import com.xzg.mall.dao.PickAddrMapper;
import com.xzg.mall.service.PickAddrService;

/**
 *
 * @author hutao on 2018/10/17.
 */
@Service
public class PickAddrServiceImpl extends ServiceImpl<PickAddrMapper, PickAddr> implements PickAddrService {

    @Autowired
    private PickAddrMapper pickAddrMapper;

	@Override
	public void deleteByIds(Long[] ids) {
		pickAddrMapper.deleteByIds(ids);
	}


}
