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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzg.mall.bean.model.Area;
import com.xzg.mall.dao.AreaMapper;
import com.xzg.mall.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lgh on 2018/10/26.
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    @Cacheable(cacheNames = "area", key = "#pid")
    public List<Area> listByPid(Long pid) {
        return areaMapper.selectList(new LambdaQueryWrapper<Area>().eq(Area::getParentId, pid));
    }

    @Override
    @CacheEvict(cacheNames = "area", key = "#pid")
    public void removeAreaCacheByParentId(Long pid) {

    }
}