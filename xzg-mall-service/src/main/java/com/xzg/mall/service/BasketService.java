package com.xzg.mall.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.app.dto.ShopCartDto;
import com.xzg.mall.bean.app.dto.ShopCartItemDto;
import com.xzg.mall.bean.app.param.ChangeShopCartParam;
import com.xzg.mall.bean.app.param.OrderItemParam;
import com.xzg.mall.bean.app.param.ShopCartParam;
import com.xzg.mall.bean.model.Basket;

/**
 * @author hutao
 */
public interface BasketService extends IService<Basket> {

    void deleteShopCartItemsByBasketIds(String userId, List<Long> basketIds);

    void addShopCartItem(ChangeShopCartParam param, String userId);

    void updateShopCartItem(Basket basket);

    void deleteAllShopCartItems(String userId);

    List<ShopCartItemDto> getShopCartItems(String userId);

    List<ShopCartItemDto> getShopCartExpiryItems(String userId);

    void cleanExpiryProdList(String userId);

    void updateBasketByShopCartParam(String userId, Map<Long, ShopCartParam> basketIdShopCartParamMap);

    void removeShopCartItemsCacheByUserId(String userId);

    /**
     * 获取购物车中拥有某件商品的用户，用于清除该用户购物车的缓存
     * @param prodId 商品id
     * @return 用户id
     */
    List<String> listUserIdByProdId(Long prodId);

    /**
     * 根据店铺组装购车中的商品信息，返回每个店铺中的购物车商品信息
     * @param shopCartItems 指定的购物项目
     * @return 每个店铺的购物车信息
     */
    List<ShopCartDto> getShopCarts(List<ShopCartItemDto> shopCartItems);

    /**
     * 组装获取用户提交的购物车商品项
     * @param orderItems 提交订单时携带的商品信息
     * @param userId 当前用户的用户id
     * @return 所有的商品购物项
     */
    List<ShopCartItemDto> getShopCartItemsByOrderItems(List<Long> basketId, OrderItemParam orderItem,String userId);

}
