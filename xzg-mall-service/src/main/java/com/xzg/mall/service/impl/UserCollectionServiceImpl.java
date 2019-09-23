package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzg.mall.bean.app.dto.UserCollectionDto;
import com.xzg.mall.bean.model.UserCollection;
import com.xzg.mall.dao.UserCollectionMapper;
import com.xzg.mall.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户收藏表
 *
 * @author hutao
 * @date 2019-04-19 16:57:20
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Autowired
    private UserCollectionMapper userCollectionMapper;

    @Override
    public IPage<UserCollectionDto> getUserCollectionDtoPageByUserId(Page page, String userId) {
        return userCollectionMapper.getUserCollectionDtoPageByUserId(page,userId);
    }
}
