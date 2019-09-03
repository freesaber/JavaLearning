package cn.freesaber.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /** 公众平台appId和Secret */
    private String mpAppId;
    private String mpAppSecret;

    /** 开发平台appId和Secret */
    private String openAppId;
    private String openAppSecret;

    /** 商户号 */
    private String mchId;

    /** 商户密钥 */
    private String mchKey;

    /** 商户证书路径 */
    private String keyPath;

    /** 微信异步通知地址 */
    private String notifyUrl;
}
