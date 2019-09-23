package com.xzg.mall.security.token;

import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 二维码Token
 */
@NoArgsConstructor
public class MpAuthenticationToken extends MyAuthenticationToken {


    public MpAuthenticationToken(UserDetails principal, Object credentials) {
        super(principal, credentials, principal.getAuthorities());
    }
}
