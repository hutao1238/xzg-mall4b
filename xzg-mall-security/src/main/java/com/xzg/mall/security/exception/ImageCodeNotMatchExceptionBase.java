package com.xzg.mall.security.exception;

public class ImageCodeNotMatchExceptionBase extends BaseXzgAuth2Exception {

    public ImageCodeNotMatchExceptionBase(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "image_code_not_match";
    }
}
