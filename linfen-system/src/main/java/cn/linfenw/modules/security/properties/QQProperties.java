package cn.linfenw.modules.security.properties;


import lombok.Data;

/**
 * @Classname QQProperties
 * @Description QQ第三方登录配置
 * @Author linfen
 * @Date 2019-07-08 20:16
 * @Version 1.0
 */
@Data
public class QQProperties extends SocialProperties{

    private String providerId = "qq";
}
