package com.xzg.mall.security.exception;

import org.springframework.http.HttpStatus;

public class UnknownGrantTypeExceptionBase extends BaseXzgAuth2Exception {

    public UnknownGrantTypeExceptionBase(String msg) {
        super(msg);
    }


    public UnknownGrantTypeExceptionBase(String msg, Throwable t) {
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