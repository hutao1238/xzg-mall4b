package com.xzg.mall.bean.event;

import com.xzg.mall.bean.app.dto.ShopCartDto;
import com.xzg.mall.bean.app.dto.ShopCartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 购物车商品发生改变时的事件
 * @author hutao
 */
@Data
@AllArgsConstructor
public class ShopCartEvent {

    /**
     * 将要组装的单个店铺的店铺信息
     */
    private ShopCartDto shopCartDto;

    /**
     * 该店铺下的所有商品信息
     */
    private List<ShopCartItemDto> shopCartItemDtoList;
}
