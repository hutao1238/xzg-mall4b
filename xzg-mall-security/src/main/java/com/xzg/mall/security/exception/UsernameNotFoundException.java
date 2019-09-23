package com.xzg.mall.security.exception;

public class UsernameNotFoundException extends XzgAuth2Exception {

    public UsernameNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "username_not_found";
    }
}
