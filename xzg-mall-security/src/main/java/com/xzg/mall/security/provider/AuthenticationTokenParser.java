package com.xzg.mall.security.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * AuthenticationTokenParser
 *
 * @author hutao
 * @date 2019-08-21
 */
public interface AuthenticationTokenParser {
    AbstractAuthenticationToken parse(String authenticationTokenStr);
}
