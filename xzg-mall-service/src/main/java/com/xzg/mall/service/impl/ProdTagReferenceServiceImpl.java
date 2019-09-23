package com.xzg.mall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzg.mall.bean.model.ProdTagReference;
import com.xzg.mall.dao.ProdTagReferenceMapper;
import com.xzg.mall.service.ProdTagReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分组标签引用
 *
 * @author hutao
 * @date 2019-04-18 16:28:01
 */
@Service
public class ProdTagReferenceServiceImpl extends ServiceImpl<ProdTagReferenceMapper, ProdTagReference> implements ProdTagReferenceService {

    @Autowired
    private ProdTagReferenceMapper prodTagReferenceMapper;

    @Override
    public List<Long> listTagIdByProdId(Long prodId) {
        return prodTagReferenceMapper.listTagIdByProdId(prodId);
    }
}
