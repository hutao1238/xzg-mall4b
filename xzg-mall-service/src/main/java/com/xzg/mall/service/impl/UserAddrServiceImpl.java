package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzg.mall.bean.model.UserAddr;
import com.xzg.mall.common.exception.XzgShopBindException;
import com.xzg.mall.dao.UserAddrMapper;
import com.xzg.mall.service.UserAddrService;

@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements UserAddrService {

	@Autowired
	private UserAddrMapper userAddrMapper;

	@Override
	public UserAddr getDefaultUserAddr(String userId) {
		return userAddrMapper.getDefaultUserAddr(userId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateDefaultUserAddr(Long addrId, String userId) {
		userAddrMapper.removeDefaultUserAddr(userId);
		int setCount = userAddrMapper.setDefaultUserAddr(addrId,userId);
		if (setCount == 0) {
			throw new XzgShopBindException("无法修改用户默认地址，请稍后再试");
		}
	}

    @Override
	@CacheEvict(cacheNames = "UserAddrDto", key = "#userId+':'+#addrId")
    public void removeUserAddrByUserId(Long addrId, String userId) {

    }

	@Override
	@Cacheable(cacheNames = "UserAddrDto", key = "#userId+':'+#addrId")
	public UserAddr getUserAddrByUserId(Long addrId, String userId) {
		if (addrId == 0) {
			return userAddrMapper.getDefaultUserAddr(userId);
		}
		return userAddrMapper.getUserAddrByUserIdAndAddrId(userId, addrId);
	}


}
