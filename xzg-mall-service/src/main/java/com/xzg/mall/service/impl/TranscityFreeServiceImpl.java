package com.xzg.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzg.mall.bean.model.TranscityFree;
import com.xzg.mall.dao.TranscityFreeMapper;

import com.xzg.mall.service.TranscityFreeService;

/**
 *
 * @author lgh on 2018/12/20.
 */
@Service
public class TranscityFreeServiceImpl extends ServiceImpl<TranscityFreeMapper, TranscityFree> implements TranscityFreeService {

    @Autowired
    private TranscityFreeMapper transcityFreeMapper;

}
