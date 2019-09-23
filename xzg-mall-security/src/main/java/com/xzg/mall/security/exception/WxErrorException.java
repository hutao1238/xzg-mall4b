package com.xzg.mall.security.exception;

/**
 * 密码不正确
 */
public class WxErrorException extends XzgAuth2Exception {
    public WxErrorException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "wx_error_exception";
    }
}
