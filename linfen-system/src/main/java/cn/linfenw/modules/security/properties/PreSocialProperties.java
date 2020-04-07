package cn.linfenw.modules.security.properties;

import lombok.Data;

/**
 * @author linfen
 */
@Data
public class PreSocialProperties {

    /**
     * 默认认证页面
     */
    private String filterProcessesUrl = "/auth";
    private QQProperties qq = new QQProperties();
    private GithubProperties github = new GithubProperties();
    private GiteeProperties gitee = new GiteeProperties();
    private WeiXinProperties weiXin = new WeiXinProperties();


}
