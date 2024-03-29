package com.xzg.mall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzg.mall.bean.model.ProdTagReference;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分组标签引用
 *
 * @author hutao
 * @date 2019-04-18 16:28:01
 */
public interface ProdTagReferenceMapper extends BaseMapper<ProdTagReference> {
    void insertBatch(@Param("shopId") Long shopId, @Param("prodId") Long prodId, @Param("tagList") List<Long> tagList);

    List<Long> listTagIdByProdId(@Param("prodId") Long prodId);

}
