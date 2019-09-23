package com.xzg.mall.security.exception;

/**
 * 密码不正确
 */
public class BadCredentialsExceptionBase extends BaseXzgAuth2Exception {
    public BadCredentialsExceptionBase(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "bad_credentials";
    }
}
