package com.xzg.mall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.app.dto.UserCollectionDto;
import com.xzg.mall.bean.model.UserCollection;

/**
 * 用户收藏表
 *
 * @author hutao
 * @date 2019-04-19 16:57:20
 */
public interface UserCollectionService extends IService<UserCollection> {
    IPage<UserCollectionDto> getUserCollectionDtoPageByUserId(Page page, String userId);
}
