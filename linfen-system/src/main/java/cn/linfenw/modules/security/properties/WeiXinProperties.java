package cn.linfenw.modules.security.properties;

import lombok.Data;

/**
 * @Classname GithubProperties
 * @Description github第三方登录配置
 * @Author linfen
 * @Date 2019-07-08 21:49
 * @Version 1.0
 */
@Data
public class WeiXinProperties extends SocialProperties {

    private String providerId = "weixin";
}
