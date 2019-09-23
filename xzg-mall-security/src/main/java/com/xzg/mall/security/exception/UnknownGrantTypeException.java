package com.xzg.mall.security.exception;

import org.springframework.http.HttpStatus;

public class UnknownGrantTypeException extends XzgAuth2Exception {

    public UnknownGrantTypeException(String msg) {
        super(msg);
    }


    public UnknownGrantTypeException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "unknown_grant_type";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

}