package com.xzg.mall.admin.security;

import com.xzg.mall.security.filter.LoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private LoginAuthenticationFilter loginAuthenticationFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/webjars/**",
                        "/swagger/**",
                        "/v2/api-docs",
                        "/doc.html",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/captcha.jpg").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();//配置所有访问控制，必须认证过后才可以访问
        // @formatter:on
    }


}
