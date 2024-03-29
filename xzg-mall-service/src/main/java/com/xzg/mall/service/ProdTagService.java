package com.xzg.mall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.ProdTag;

import java.util.List;

/**
 * 商品分组标签
 *
 * @author hutao
 * @date 2019-04-18 10:48:44
 */
public interface ProdTagService extends IService<ProdTag> {

    List<ProdTag> listProdTag();

    void removeProdTag();
}
