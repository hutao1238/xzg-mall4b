package com.xzg.mall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.app.dto.ProdCommDataDto;
import com.xzg.mall.bean.app.dto.ProdCommDto;
import com.xzg.mall.bean.model.ProdComm;


/**
 * 商品评论
 *
 * @author hutao
 * @date 2019-04-19 10:43:57
 */
public interface ProdCommService extends IService<ProdComm> {
    ProdCommDataDto getProdCommDataByProdId(Long prodId, String userId);

    IPage<ProdCommDto> getProdCommDtoPageByUserId(Page page,String userId);

    IPage<ProdCommDto> getProdCommDtoPageByProdId(Page page, Long prodId, Integer evaluate, String userId);

    IPage<ProdComm> getProdCommPage(Page page,ProdComm prodComm);

}
