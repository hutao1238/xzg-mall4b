/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.api.listener;

import com.xzg.mall.bean.app.dto.ShopCartItemDto;
import com.xzg.mall.bean.app.dto.ShopCartOrderDto;
import com.xzg.mall.bean.app.param.OrderParam;
import com.xzg.mall.bean.event.ConfirmOrderEvent;
import com.xzg.mall.bean.model.Product;
import com.xzg.mall.bean.model.Sku;
import com.xzg.mall.bean.model.UserAddr;
import com.xzg.mall.bean.order.ConfirmOrderOrder;
import com.xzg.mall.common.exception.YamiShopBindException;
import com.xzg.mall.common.util.Arith;
import com.xzg.mall.security.util.SecurityUtils;
import com.xzg.mall.service.ProductService;
import com.xzg.mall.service.SkuService;
import com.xzg.mall.service.TransportManagerService;
import com.xzg.mall.service.UserAddrService;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 确认订单信息时的默认操作
 * @author LGH
 */
@Component("defaultConfirmOrderListener")
@AllArgsConstructor
public class ConfirmOrderListener {

    private final UserAddrService userAddrService;

    private final TransportManagerService transportManagerService;

    private final ProductService productService;

    private final SkuService skuService;

    /**
     * 计算订单金额
     */
    @EventListener(ConfirmOrderEvent.class)
    @Order(ConfirmOrderOrder.DEFAULT)
    public void defaultConfirmOrderEvent(ConfirmOrderEvent event) {


        ShopCartOrderDto shopCartOrderDto = event.getShopCartOrderDto();

        OrderParam orderParam = event.getOrderParam();

        String userId = SecurityUtils.getUser().getUserId();

        // 订单的地址信息
        UserAddr userAddr = userAddrService.getUserAddrByUserId(orderParam.getAddrId(), userId);

        double total = 0.0;

        int totalCount = 0;

        double transfee = 0.0;

        for (ShopCartItemDto shopCartItem : event.getShopCartItems()) {
            // 获取商品信息
            Product product = productService.getProductByProdId(shopCartItem.getProdId());
            // 获取sku信息
            Sku sku = skuService.getSkuBySkuId(shopCartItem.getSkuId());
            if (product == null || sku == null) {
                throw new YamiShopBindException("购物车包含无法识别的商品");
            }
            if (product.getStatus() != 1 || sku.getStatus() != 1) {
                throw new YamiShopBindException("商品[" + sku.getProdName() + "]已下架");
            }

            totalCount = shopCartItem.getProdCount() + totalCount;
            total = Arith.add(shopCartItem.getProductTotalAmount(), total);
            // 用户地址如果为空，则表示该用户从未设置过任何地址相关信息
            if (userAddr != null) {
                // 每个产品的运费相加
                transfee = Arith.add(transfee, transportManagerService.calculateTransfee(shopCartItem, userAddr));
            }

            shopCartItem.setActualTotal(shopCartItem.getProductTotalAmount());
            shopCartOrderDto.setActualTotal(Arith.sub(total, transfee));
            shopCartOrderDto.setTotal(total);
            shopCartOrderDto.setTotalCount(totalCount);
            shopCartOrderDto.setTransfee(transfee);
        }
    }
}
