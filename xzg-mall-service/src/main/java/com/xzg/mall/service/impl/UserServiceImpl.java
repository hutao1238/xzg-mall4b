package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzg.mall.bean.model.User;
import com.xzg.mall.dao.UserMapper;
import com.xzg.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author hutao on 2018/09/11.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @Cacheable(cacheNames="user",key="#userId")
    public User getUserByUserId(String userId) {
        return userMapper.selectById(userId);
    }
}
