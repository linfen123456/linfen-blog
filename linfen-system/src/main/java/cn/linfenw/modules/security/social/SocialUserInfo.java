package cn.linfenw.modules.security.social;

import lombok.Data;

/**
 * @Classname SocialUserInfo
 * @Description
 * @Author linfen
 * @Date 2019-07-08 21:49
 * @Version 1.0
 */
@Data
public class SocialUserInfo {

    private String providerId;
    private String providerUserId;
    private String nickname;
    private String headImg;
}
