package com.xzg.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzg.mall.bean.enums.ProdPropRule;
import com.xzg.mall.bean.model.ProdProp;
import com.xzg.mall.common.exception.XzgShopBindException;
import com.xzg.mall.common.util.PageAdapter;
import com.xzg.mall.dao.CategoryPropMapper;
import com.xzg.mall.dao.ProdPropMapper;
import com.xzg.mall.dao.ProdPropValueMapper;
import com.xzg.mall.service.ProdPropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * Created by lgh on 2018/07/06.
 */
@Service
public class ProdPropServiceImpl extends ServiceImpl<ProdPropMapper, ProdProp> implements ProdPropService {

    @Autowired
    private ProdPropMapper prodPropMapper;

    @Autowired
    private ProdPropValueMapper prodPropValueMapper;

    @Autowired
    private CategoryPropMapper categoryPropMapper;

    @Override
    public IPage<ProdProp> pagePropAndValue(ProdProp prodProp, Page<ProdProp> page) {

        page.setRecords(prodPropMapper.listPropAndValue(new PageAdapter(page), prodProp));
        page.setTotal(prodPropMapper.countPropAndValue(prodProp));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProdPropAndValues(@Valid ProdProp prodProp) {
        ProdProp dbProdProp = prodPropMapper.getProdPropByPropNameAndShopId(prodProp.getPropName(), prodProp.getShopId(), prodProp.getRule());
        if (dbProdProp != null) {
            throw new XzgShopBindException("已有相同名称规格");
        }
        prodPropMapper.insert(prodProp);
        if (CollUtil.isEmpty(prodProp.getProdPropValues())) {
            return;
        }
        prodPropValueMapper.insertPropValues(prodProp.getPropId(), prodProp.getProdPropValues());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProdPropAndValues(ProdProp prodProp) {
        ProdProp dbProdProp = prodPropMapper.getProdPropByPropNameAndShopId(prodProp.getPropName(), prodProp.getShopId(), prodProp.getRule());
        if (dbProdProp != null && !Objects.equals(prodProp.getPropName(), dbProdProp.getPropName())) {
            throw new XzgShopBindException("已有相同名称规格");
        }
        prodPropMapper.updateById(prodProp);
        // 先删除原有的属性值，再添加新的属性值
        prodPropValueMapper.deleteByPropId(prodProp.getPropId());
        if (CollUtil.isEmpty(prodProp.getProdPropValues())) {
            return;
        }
        prodPropValueMapper.insertPropValues(prodProp.getPropId(), prodProp.getProdPropValues());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProdPropAndValues(Long propId, Integer propRule, Long shopId) {
        int deleteRows = prodPropMapper.deleteByPropId(propId, propRule, shopId);
        if (deleteRows == 0) {
            return;
        }
        // 删除原有的属性值
        prodPropValueMapper.deleteByPropId(propId);

        // 如果是参数，删除参数与分类关联信息
        if (ProdPropRule.ATTRIBUTE.value().equals(propRule)) {
            categoryPropMapper.deleteByPropId(propId);
        }
    }

    @Override
    public List<ProdProp> listByCategoryId(Long categoryId) {
        return prodPropMapper.listByCategoryId(categoryId);
    }
}
