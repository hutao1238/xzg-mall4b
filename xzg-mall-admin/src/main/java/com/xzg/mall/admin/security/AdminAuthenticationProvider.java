package com.xzg.mall.admin.security;


import cn.hutool.core.util.StrUtil;
import com.xzg.mall.common.util.RedisUtil;
import com.xzg.mall.security.constants.SecurityConstants;
import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.exception.BadCredentialsExceptionBase;
import com.xzg.mall.security.exception.ImageCodeNotMatchExceptionBase;
import com.xzg.mall.security.exception.UsernameNotFoundExceptionBase;
import com.xzg.mall.security.exception.BaseXzgAuth2Exception;
import com.xzg.mall.security.provider.AbstractUserDetailsAuthenticationProvider;
import com.xzg.mall.security.service.XzgUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 后台管理员账号密码登陆
 * @author hutao
 */
@Component
@AllArgsConstructor
public class AdminAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final XzgUserDetailsService xzgUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected UserDetails retrieveUser(String username, Authentication authentication) throws BaseXzgAuth2Exception {

        AdminAuthenticationToken adminAuthenticationToken = (AdminAuthenticationToken) authentication;

        String kaptchaKey = SecurityConstants.SPRING_SECURITY_RESTFUL_IMAGE_CODE + adminAuthenticationToken.getSessionUUID();

        String kaptcha = RedisUtil.get(kaptchaKey);

        RedisUtil.del(kaptchaKey);

        if(StrUtil.isBlank(adminAuthenticationToken.getImageCode()) || !adminAuthenticationToken.getImageCode().equalsIgnoreCase(kaptcha)){
            throw new ImageCodeNotMatchExceptionBase("验证码有误");
        }

        UserDetails user;
        try {
            user = xzgUserDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundExceptionBase var6) {
            throw new UsernameNotFoundExceptionBase("账号或密码不正确");
        }

        String encodedPassword = user.getPassword();
        String rawPassword = authentication.getCredentials().toString();

        // 密码不正确
        if (!passwordEncoder.matches(rawPassword,encodedPassword)){
            throw new BadCredentialsExceptionBase("账号或密码不正确");
        }

        if (!user.isEnabled()) {
            throw new UsernameNotFoundExceptionBase("账号已被锁定,请联系管理员");
        }
        return user;
    }


    @Override
    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
        AdminAuthenticationToken result = new AdminAuthenticationToken(user, authentication.getCredentials());
        result.setDetails(authentication.getDetails());
        return result;
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return AdminAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected App getAppInfo() {
        return null;
    }

}
