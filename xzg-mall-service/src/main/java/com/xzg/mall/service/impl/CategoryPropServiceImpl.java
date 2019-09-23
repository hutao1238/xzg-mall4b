package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.CategoryProp;
import com.xzg.mall.dao.CategoryPropMapper;
import com.xzg.mall.service.CategoryPropService;

/**
 *
 * Created by lgh on 2018/07/13.
 */
@Service
public class CategoryPropServiceImpl extends ServiceImpl<CategoryPropMapper, CategoryProp> implements CategoryPropService {

    @Autowired
    private CategoryPropMapper categoryPropMapper;

}
