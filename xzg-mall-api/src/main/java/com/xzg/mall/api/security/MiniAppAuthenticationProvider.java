package com.xzg.mall.api.security;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.BooleanUtil;
import com.xzg.mall.security.enums.App;
import com.xzg.mall.security.exception.UsernameNotFoundExceptionBase;
import com.xzg.mall.security.exception.WxErrorExceptionBase;
import com.xzg.mall.security.model.AppConnect;
import com.xzg.mall.security.provider.AbstractUserDetailsAuthenticationProvider;
import com.xzg.mall.security.service.XzgUser;
import com.xzg.mall.security.service.XzgUserDetailsService;
import com.xzg.mall.security.token.MyAuthenticationToken;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 小程序登陆
 * @author hutao
 */
@Component
@AllArgsConstructor
public class MiniAppAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final XzgUserDetailsService xzgUserDetailsService;

    private final WxMaService wxMaService;

    @Override
    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
        MiniAppAuthenticationToken result = new MiniAppAuthenticationToken(user, authentication.getCredentials());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    protected UserDetails retrieveUser(String code, Authentication authentication) throws AuthenticationException {
        XzgUser loadedUser = null;
        // 如果使用debugger 模式，则返回debugger的用户
        if (BooleanUtil.isTrue(((MyAuthenticationToken)authentication).getDebugger())) {
            loadedUser = new XzgUser("1" , "debuggerOpenId1" ,  this.getAppInfo().value(), true);
            loadedUser.setDebugger(true);
            return loadedUser;
        }

        WxMaJscode2SessionResult session = null;

        AppConnect appConnect = new AppConnect();
        appConnect.setAppId(this.getAppInfo().value());
        try {

            session = wxMaService.getUserService().getSessionInfo(code);

            loadedUser = xzgUserDetailsService.loadUserByAppIdAndBizUserId(this.getAppInfo(),session.getOpenid());
        } catch (WxErrorException e) {
            throw new WxErrorExceptionBase(e.getMessage());
        } catch (UsernameNotFoundExceptionBase var6) {
            if (session == null) {
                throw new WxErrorExceptionBase("无法获取用户登陆信息");
            }
            appConnect.setBizUserId(session.getOpenid());
            appConnect.setBizUnionid(session.getUnionid());
            xzgUserDetailsService.insertUserIfNecessary(appConnect);
        }

        if (loadedUser == null) {
            loadedUser = xzgUserDetailsService.loadUserByAppIdAndBizUserId(this.getAppInfo(), appConnect.getBizUserId());
        }
        return loadedUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MiniAppAuthenticationToken.class.isAssignableFrom(authentication);
    }


    @Override
    protected App getAppInfo() {
        return App.MINI;
    }
}
