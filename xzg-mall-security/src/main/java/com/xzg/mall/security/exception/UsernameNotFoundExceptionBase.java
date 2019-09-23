package com.xzg.mall.security.exception;

public class UsernameNotFoundExceptionBase extends BaseXzgAuth2Exception {

    public UsernameNotFoundExceptionBase(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "username_not_found";
    }
}
