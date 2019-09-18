package com.xzg.mall.api.security;

import com.xzg.mall.common.util.Json;
import com.xzg.mall.security.provider.AuthenticationTokenParser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * MiniAppAuthenticationTokenParser
 *
 * @author hanfeng
 * @date 2019-08-21
 */
@Component
public class MiniAppAuthenticationTokenParser implements AuthenticationTokenParser {
    @Override
    public AbstractAuthenticationToken parse(String authenticationTokenStr) {
        MiniAppAuthenticationToken  authRequest = Json.parseObject(authenticationTokenStr, MiniAppAuthenticationToken.class);
        return authRequest;
    }
}
