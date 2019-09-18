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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.ProdPropValue;
import com.xzg.mall.dao.ProdPropValueMapper;
import com.xzg.mall.service.ProdPropValueService;

/**
 *
 * Created by lgh on 2018/07/06.
 */
@Service
public class ProdPropValueServiceImpl extends ServiceImpl<ProdPropValueMapper, ProdPropValue> implements ProdPropValueService {

    @Autowired
    private ProdPropValueMapper prodPropValueMapper;

}
