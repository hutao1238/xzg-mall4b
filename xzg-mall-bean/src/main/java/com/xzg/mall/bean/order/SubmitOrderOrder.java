package com.xzg.mall.bean.order;

/**
 * 提交订单事件先后顺序
 * @author hutao
 */
public interface SubmitOrderOrder {

    /**
     * 没有任何活动时的顺序
     */
    int DEFAULT = 0;

    /**
     * 优惠券，排在DEFAULT后面
     */
    int DISCOUNT = 100;

    /**
     * 优惠券，排在DEFAULT后面
     */
    int COUPON = 200;
}
