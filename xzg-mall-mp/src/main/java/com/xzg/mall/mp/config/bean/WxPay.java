package com.xzg.mall.mp.config.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:pay.properties")
@ConfigurationProperties(prefix = "pay")
public class WxPay {
    /**
     * 微信支付mchId
     */
    private String mchId;
    
    /**
     * 微信支付mchKey
     */
    private String mchKey;
    
    /**
     * 签名类型
     */
    private String signType;

	/**
	 * 支付证书路径
	 */
	private String keyPath;

    
}
