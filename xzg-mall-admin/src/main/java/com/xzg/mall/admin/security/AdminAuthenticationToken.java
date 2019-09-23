package com.xzg.mall.admin.security;

import com.xzg.mall.security.token.MyAuthenticationToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 系统用户账号密码登陆
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminAuthenticationToken extends MyAuthenticationToken {

    private String sessionUUID;

    private String imageCode;

    public AdminAuthenticationToken(UserDetails principal, Object credentials) {
        super(principal, credentials, principal.getAuthorities());
    }


}
