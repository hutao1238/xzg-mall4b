package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.Transfee;
import com.xzg.mall.dao.TransfeeMapper;
import com.xzg.mall.service.TransfeeService;

/**
 *
 * @author lgh on 2018/11/16.
 */
@Service
public class TransfeeServiceImpl extends ServiceImpl<TransfeeMapper, Transfee> implements TransfeeService {

    @Autowired
    private TransfeeMapper transfeeMapper;

}
