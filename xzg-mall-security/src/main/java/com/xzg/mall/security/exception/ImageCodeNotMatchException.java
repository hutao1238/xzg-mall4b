package com.xzg.mall.security.exception;

public class ImageCodeNotMatchException extends XzgAuth2Exception {

    public ImageCodeNotMatchException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "image_code_not_match";
    }
}
