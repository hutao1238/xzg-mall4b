package com.xzg.mall.security.provider;

import com.xzg.mall.security.enums.App;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义 AuthenticationProvider， 以使用自定义的 MyAuthenticationToken
 * @author hutao
 */
@Slf4j
public abstract class AbstractUserDetailsAuthenticationProvider implements AuthenticationProvider, InitializingBean {



    @Override
    public final void afterPropertiesSet() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() == null?"NONE_PROVIDED":authentication.getName();
        UserDetails user;

        try {
            user = this.retrieveUser(username, authentication);
        } catch (UsernameNotFoundException var6) {
            log.debug("User \'" + username + "\' not found");

            throw var6;
        }

        return this.createSuccessAuthentication(authentication, user);
    }

    protected abstract Authentication createSuccessAuthentication(Authentication authentication, UserDetails user);

    protected abstract App getAppInfo();

    protected abstract UserDetails retrieveUser(String username, Authentication authentication) throws AuthenticationException;



}
