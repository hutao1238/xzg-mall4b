package com.xzg.mall.common.enums;

/**
 * 与前端进行特殊交互需要使用的状态码，由于小程序需要，所以状态码只能为3位数字，并且不能与正常的http状态码冲突
 * @author hutao
 */
public enum XzgHttpStatus {
    /**
     * 客户端看到401状态码时，应该重新登陆
     */
    UNAUTHORIZED(401, "未授权"),

    COUPONCANNOTUSETOGETHER(601, "优惠券不能共用"),
    ;


    private final int value;

    private final String msg;


    XzgHttpStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }


    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the msg of this status code.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return this.value + " " + name();
    }


    public static XzgHttpStatus valueOf(int statusCode) {
        XzgHttpStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("没有找到该Http状态码包含状态 [" + statusCode + "]");
        }
        return status;
    }

    public static XzgHttpStatus resolve(int statusCode) {
        for (XzgHttpStatus status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }


}
