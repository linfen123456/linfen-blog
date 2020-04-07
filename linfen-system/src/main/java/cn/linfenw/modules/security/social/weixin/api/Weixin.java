package cn.linfenw.modules.security.social.weixin.api;


/**
 * @Classname Weixin
 * @Description 微信API调用接口
 * @Author linfen
 * @Date 2019-08-23 16:50
 * @Version 1.0
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
