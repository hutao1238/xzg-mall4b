package com.xzg.mall.mp.config.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:mp.properties")
@ConfigurationProperties(prefix = "mp")
public class WxMp {
    /**
     * 设置微信公众号的appid
     */
    private String appid;

    /**
     * 设置微信公众号的Secret
     */
    private String secret;
    
    /**
     * 微信公众号消息加解密token
     */
    private String token;
    
    /**
     * 微信公众号消息加解密aesKey
     */
    private String aesKey;
}
