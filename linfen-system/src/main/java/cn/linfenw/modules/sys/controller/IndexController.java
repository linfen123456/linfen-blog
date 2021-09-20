package cn.linfenw.modules.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.linfenw.common.exception.ValidateCodeException;
import cn.linfenw.modules.sys.domain.SysSocial;
import cn.linfenw.modules.sys.domain.SysUser;
import cn.linfenw.modules.sys.dto.UserDTO;
import cn.linfenw.modules.security.code.sms.SmsCodeService;
import cn.linfenw.modules.security.social.PreConnectionData;
import cn.linfenw.modules.security.social.SocialRedisHelper;
import cn.linfenw.modules.security.social.SocialUserInfo;
import cn.linfenw.modules.sys.service.ISysSocialService;
import cn.linfenw.modules.sys.service.ISysUserService;
import cn.linfenw.common.utils.R;
import cn.linfenw.security.util.SecurityUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.*;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Classname IndexController
 * @Description 主页模块
 * @Author linfen
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@RestController
public class IndexController {

    @Autowired
    private ISysUserService userService;


    @Autowired
    private SmsCodeService smsCodeService;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private SocialRedisHelper socialRedisHelper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ISysSocialService socialService;

    @Value("${linfen-blog.oauth.callBackBaseUrl}")
    private String callBackBaseUrl ;//= "http://www.linfenw.cn:7200/fkdp-boot/oauth/callback"
    @Value("${linfen-blog.oauth.vueBaseUrl}")
    private String url;//= "http://127.0.0.1:3000"
//    private String callBackBaseUrl="http://127.0.0.1:8080/oauth/callback";


    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO) {
        String redisCode = (String) redisTemplate.opsForValue().get(userDTO.getEmail());
        if (ObjectUtil.isNull(redisCode)) {
            throw new ValidateCodeException("验证码已失效");
        }
        if (!userDTO.getSmsCode().toLowerCase().equals(redisCode.toLowerCase())) {
            throw new ValidateCodeException("邮箱验证码错误");
        }
        return R.ok(userService.register(userDTO));
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public R login(String username, String password, HttpServletRequest request) {
        // 社交快速登录
        String key = request.getParameter("token");
        if (StrUtil.isNotEmpty(key)) {
            String  token = (String) redisTemplate.opsForValue().get(key);
            return R.ok(token);
        }
        return R.ok(userService.login(username, password));
    }


    /**
     * 授权请求地址
     *
     * @param source
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/authLogin/{source}")
    public R renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        System.out.println("进入render：" + source);
        AuthRequest authRequest = getAuthRequest(source);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        System.out.println(authorizeUrl);
//        response.sendRedirect(authorizeUrl);
        return R.ok(authorizeUrl);
    }

    /**
     * oauth平台中配置的授权回调地址，以本项目为例，在创建github授权应用时的回调地址应为：http://127.0.0.1:8080/oauth/callback/github
     */
    @RequestMapping("/callback/{source}")
    public Object callback(@PathVariable("source") String source, AuthCallback callback, HttpServletResponse response) throws IOException {
        String uuid = "";
        System.out.println("进入callback：" + source + " callback params：" +
                JSONObject.toJSONString(callback));
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse<AuthUser> authResponse = authRequest.login(callback);
        System.out.println(JSONObject.toJSONString(authResponse));

          /*  switch (source) {
                case "weibo":
                    authResponse.getData().getToken().getOpenId();
                    break;
            }
*/

        //通过getProviderid和getProviderUserId查询用户
        if (authResponse == null) {
            return R.error("授权失败");
        }

        System.out.println("source="+source+",getProviderUserId="+authResponse.getData().getToken().getOpenId());
        String openId=authResponse.getData().getToken().getOpenId()==null?(authResponse.getData().getUuid()==null?"":authResponse.getData().getUuid()):authResponse.getData().getToken().getOpenId();
        SysSocial sysUserSocial = socialService.lambdaQuery().eq(SysSocial::getProviderId, source)
                .eq(SysSocial::getProviderUserId, openId).one();
        if (sysUserSocial == null) {
            AuthToken token = authResponse.getData().getToken();
            sysUserSocial = new SysSocial();
            sysUserSocial.setProviderId(source);
            sysUserSocial.setProviderUserId(openId);
            sysUserSocial.setAccessToken(token.getAccessToken());
            sysUserSocial.setRefreshToken(token.getRefreshToken());
            sysUserSocial.setExpireTime(token.getExpireIn()+"");
            sysUserSocial.setDisplayName(authResponse.getData().getUsername());
            sysUserSocial.setProfileUrl(authResponse.getData().getBlog());
            sysUserSocial.setImageUrl(authResponse.getData().getAvatar());
            sysUserSocial.setSecret(token.getOauthTokenSecret());
            PreConnectionData preConnectionData=new PreConnectionData();
            BeanUtil.copyProperties(sysUserSocial,preConnectionData);
            //TODO 社交账户未绑定用户数据库，进行绑定
            uuid = UUID.randomUUID().toString();
            socialRedisHelper.saveConnectionData(uuid,preConnectionData);
            System.out.println("用户绑定存数据的key"+uuid);
            response.sendRedirect(url + "/bind?key=" + uuid);
            return "redirect:";
        }



        //通过用户ID超找用户，并进行登录
        SysUser user = userService.getById(sysUserSocial.getUserId());
        String token = userService.auth(user.getPhone());
        uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(uuid,token,1, TimeUnit.MINUTES);
        System.out.println("存数据的key="+uuid);
        response.sendRedirect(url + "/login?key=" + uuid);
        return R.ok(uuid);
    }

    /**
     * 刷新Token
     *
     * @param source
     * @param token
     * @return
     */
    @RequestMapping("/refresh/{source}")
    public Object refreshAuth(@PathVariable("source") String source, String token) {
        AuthRequest authRequest = getAuthRequest(source);
        return   authRequest.refresh(AuthToken.builder().refreshToken(token).build());
    }

    /**
     * 保存完信息然后跳转到绑定用户信息页面
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/socialSignUp")
    public void socialSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = UUID.randomUUID().toString();
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setHeadImg(connectionFromSession.getImageUrl());
        userInfo.setNickname(connectionFromSession.getDisplayName());
        userInfo.setProviderId(connectionFromSession.getKey().getProviderId());
        userInfo.setProviderUserId(connectionFromSession.getKey().getProviderUserId());
        ConnectionData data = connectionFromSession.createData();
        PreConnectionData preConnectionData = new PreConnectionData();
        BeanUtil.copyProperties(data, preConnectionData);
        socialRedisHelper.saveConnectionData(uuid, preConnectionData);
        // 跳转到用户绑定页面
        response.sendRedirect(url + "/bind?key=" + uuid);
    }

    /**
     * 社交登录绑定
     *
     * @param user
     * @return
     */
    @PostMapping("/bind")
    public R register(@RequestBody SysUser user) {
        return R.ok(userService.doPostSignUp(user));
    }


    /**
     * @Author
     * @Description 暂时这样写
     * @Date 08:12 2019-06-22
     **/
    @RequestMapping("/info")
    public R info() {
        Map<String, Object> map = new HashMap<>();
        Integer userId = SecurityUtil.getUser().getUserId();
        String username = SecurityUtil.getUser().getUsername();
        Set<String> roles = userService.findRoleIdByUserId(userId);
        SysUser sysUser = userService.findByUserInfoName(username);
        sysUser.setPassword("");
        map.put("roles", roles.toArray());
        map.put("avatar", sysUser.getAvatar());
        map.put("name", sysUser.getUsername());
        map.put("nickname", sysUser.getNickname());
        map.put("user", sysUser);
        return R.ok(map);
    }

    /**
     * @Author
     * @Description 使用jwt前后分离 只需要前端清除token即可 暂时返回success
     * @Date 08:13 2019-06-22
     **/
    @RequestMapping("/logout")
    public String logout() {
        return "success";
    }

    /**
     * 根据具体的授权来源，获取授权请求工具类
     *
     * @param source
     * @return
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source) {
            case "dingtalk":
                authRequest = new AuthDingTalkRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/dingtalk")
                        .build());
                break;
            case "baidu":
                authRequest = new AuthBaiduRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/baidu")
                        .build());
                break;
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId("2a059be5309183d30769")
                        .clientSecret("a343f71a81fba0be09f396ff0b2688e279b3897a ")
                        .redirectUri(callBackBaseUrl + "/github")
                        .build());
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId("a030c1e5e6bef403e6d7debbfb6586016ce86090587fdc6ba2e53926c911fcef")
                        .clientSecret("1b61486af774e7066fe6bffe67b6d76d91952b8829f9322ca6d7820439aa65ce")
                        .redirectUri(callBackBaseUrl + "/gitee")
                        .build());
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId("1936954935")
                        .clientSecret("402343f427d8894b267553f9dac60c51")
                        .redirectUri(callBackBaseUrl + "/weibo")
                        .build());
                break;
            case "coding":
                authRequest = new AuthCodingRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/coding")
                        .build());
                break;
            case "tencentCloud":
                authRequest = new AuthTencentCloudRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/tencentCloud")
                        .build());
                break;
            case "oschina":
                authRequest = new AuthOschinaRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/oschina")
                        .build());
                break;
            case "alipay":
                // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1，所以这儿的回调地址使用的局域网内的ip
                authRequest = new AuthAlipayRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .alipayPublicKey("")
                        .redirectUri(callBackBaseUrl + "/alipay")
                        .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId("101826431")
                        .clientSecret("701df686bee5a56a02ae0e24f1440f6e")
                        .redirectUri(callBackBaseUrl + "/qq")
                        .build());
                break;
            case "wechat":
                authRequest = new AuthWeChatRequest(AuthConfig.builder()
                        .clientId("123")
                        .clientSecret("123")
                        .redirectUri(callBackBaseUrl + "/wechat")
                        .build());
                break;
            case "csdn":
                authRequest = new AuthCsdnRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/csdn")
                        .build());
                break;
            case "taobao":
                authRequest = new AuthTaobaoRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/taobao")
                        .build());
                break;
            case "google":
                authRequest = new AuthGoogleRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/google")
                        .build());
                break;
            case "facebook":
                authRequest = new AuthFacebookRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/facebook")
                        .build());
                break;
            case "douyin":
                authRequest = new AuthDouyinRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/douyin")
                        .build());
                break;
            case "linkedin":
                authRequest = new AuthLinkedinRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/linkedin")
                        .build());
                break;
            case "microsoft":
                authRequest = new AuthMicrosoftRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/microsoft")
                        .build());
                break;
            case "mi":
                authRequest = new AuthMiRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/mi")
                        .build());
                break;
            case "toutiao":
                authRequest = new AuthToutiaoRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/toutiao")
                        .build());
                break;
            case "teambition":
                authRequest = new AuthTeambitionRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/teambition")
                        .build());
                break;
            case "pinterest":
                authRequest = new AuthPinterestRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/pinterest")
                        .build());
                break;
            case "renren":
                authRequest = new AuthRenrenRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/teambition")
                        .build());
                break;
            case "stackoverflow":
                authRequest = new AuthStackOverflowRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/login_success")
                        .stackOverflowKey("")
                        .build());
                break;
            case "huawei":
                authRequest = new AuthHuaweiRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/huawei")
                        .build());
                break;
            case "wechatEnterprise":
                authRequest = new AuthHuaweiRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/wechatEnterprise")
                        .agentId("")
                        .build());
                break;
            case "kujiale":
                authRequest = new AuthKujialeRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/kujiale")
                        .build());
                break;
            case "gitlab":
                authRequest = new AuthGitlabRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/gitlab")
                        .build());
                break;
            case "meituan":
                authRequest = new AuthMeituanRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/meituan")
                        .build());
                break;
            case "eleme":
                authRequest = new AuthElemeRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/eleme")
                        .build());
                break;

            case "twitter":
                authRequest = new AuthTwitterRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri(callBackBaseUrl + "/twitter")
                        .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }
}
