package com.xzg.mall.mp.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.xzg.mall.mp.config.bean.WxMiniApp;
import com.xzg.mall.mp.config.bean.WxMp;
import com.xzg.mall.mp.config.bean.WxPay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号配置文件
 *
 * @author LGH
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnClass(WxPayService.class)
public class WxPayConfiguration {

    private final WxMp wxMp;

    private final WxMiniApp wxMiniApp;

    private final WxPay wxPay;

    @Value("${spring.profiles.active}")
    private String profile;


    @Bean
    public WxPayService wxMiniPayService() {
        return getWxMpPayServiceByAppId(wxMiniApp.getAppid());
    }

    @Bean
    public WxPayService wxMpPayService() {
        return getWxMpPayServiceByAppId(wxMp.getAppid());
    }


    private WxPayService getWxMpPayServiceByAppId(String appid) {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(appid);
        payConfig.setMchId(wxPay.getMchId());
        payConfig.setMchKey(wxPay.getMchKey());
        payConfig.setKeyPath(wxPay.getKeyPath());
        payConfig.setSignType(WxPayConstants.SignType.MD5);

        WxPayService wxPayService = new WxPayServiceImpl();

//      打开下面的代码，开启沙箱模式
//        if (Objects.equals(profile, "dev")) {
//            String sandboxSignKey = null;
//            try {
//                wxPayService.setConfig(payConfig);
//                sandboxSignKey = wxPayService.getSandboxSignKey();
//            } catch (WxPayException e) {
//                e.printStackTrace();
//            }
//            payConfig.setUseSandboxEnv(true);
//            payConfig.setMchKey(sandboxSignKey);
//        }

        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
