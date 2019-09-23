package com.xzg.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzg.mall.bean.app.dto.UserCollectionDto;
import com.xzg.mall.bean.model.UserCollection;

/**
 * 用户收藏表
 *
 * @author hutao
 * @date 2019-04-19 16:57:20
 */
public interface UserCollectionMapper extends BaseMapper<UserCollection> {
   IPage<UserCollectionDto> getUserCollectionDtoPageByUserId(Page page, String userId);

}
