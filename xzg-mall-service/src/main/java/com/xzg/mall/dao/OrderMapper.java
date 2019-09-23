package com.xzg.mall.dao;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzg.mall.bean.app.dto.MyOrderDto;
import com.xzg.mall.bean.app.dto.OrderCountData;
import com.xzg.mall.bean.distribution.UserShoppingDataDto;
import com.xzg.mall.bean.model.Order;
import com.xzg.mall.bean.param.OrderParam;
import com.xzg.mall.common.util.PageAdapter;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {

    Order getOrderByOrderNumber(@Param("orderNumber") String orderNumber);

    List<Order> listOrderAndOrderItems(@Param("orderStatus") Integer orderStatus, @Param("lessThanUpdateTime") DateTime lessThanUpdateTime);

    void cancelOrders(@Param("orders") List<Order> orders);

    void confirmOrder(@Param("orders") List<Order> orders);

    void updateByToPaySuccess(@Param("orderNumbers") List<String> orderNumbers, @Param("payType") Integer payType);

    List<Order> listOrdersDetialByOrder(@Param("order") Order order, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Order> listOrdersDetialByOrderParam(@Param("adapter") PageAdapter adapter, @Param("orderParam") OrderParam orderParam);

    Long countOrderDetial(@Param("orderParam") OrderParam orderParam);

    List<Long> listBoughtProdByUserIdAndShopId(@Param("shopId") Long shopId, @Param("userId") String userId);

    UserShoppingDataDto calculateUserInShopData(@Param("shopId") Long shopId, @Param("userId") String userId);

    List<MyOrderDto> listMyOrderByUserIdAndStatus(@Param("adapter") PageAdapter adapter, @Param("userId") String userId, @Param("status") Integer status);

    Long countMyOrderByUserIdAndStatus(@Param("userId") String userId, @Param("status") Integer status);

    void deleteOrders(@Param("orders") List<Order> orders);

    OrderCountData getOrderCount(String userId);

}
