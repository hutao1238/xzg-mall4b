package com.xzg.mall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.ProdTagReference;

import java.util.List;

/**
 * 分组标签引用
 *
 * @author hzm
 * @date 2019-04-18 16:28:01
 */
public interface ProdTagReferenceService extends IService<ProdTagReference> {

    List<Long> listTagIdByProdId(Long prodId);
}
