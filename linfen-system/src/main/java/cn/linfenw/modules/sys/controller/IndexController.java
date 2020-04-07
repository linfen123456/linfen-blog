package cn.linfenw.modules.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.linfenw.common.exception.ValidateCodeException;
import cn.linfenw.modules.sys.domain.SysUser;
import cn.linfenw.modules.sys.dto.UserDTO;
import cn.linfenw.modules.security.code.sms.SmsCodeService;
import cn.linfenw.modules.security.social.PreConnectionData;
import cn.linfenw.modules.security.social.SocialRedisHelper;
import cn.linfenw.modules.security.social.SocialUserInfo;
import cn.linfenw.modules.sys.service.ISysUserService;
import cn.linfenw.common.utils.R;
import cn.linfenw.security.util.SecurityUtil;
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

/**
 * @Classname IndexController
 * @Description 主页模块
 * @Author 李号东 lihaodongmail@163.com
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

    @Value("${pre.url.address}")
    private String url;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO) {
        Object redisCode = redisTemplate.opsForValue().get(userDTO.getPhone());
        if (ObjectUtil.isNull(redisCode)) {
            throw new ValidateCodeException("验证码已失效");
        }
        if (!userDTO.getSmsCode().toLowerCase().equals(redisCode)) {
            throw new ValidateCodeException("短信验证码错误");
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
        String token = request.getParameter("token");
        if (StrUtil.isNotEmpty(token)) {
            return R.ok(token);
        }
        return R.ok(userService.login(username, password));
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
     * @Author 李号东
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
        map.put("user", sysUser);
        return R.ok(map);
    }

    /**
     * @Author 李号东
     * @Description 使用jwt前后分离 只需要前端清除token即可 暂时返回success
     * @Date 08:13 2019-06-22
     **/
    @RequestMapping("/logout")
    public String logout() {
        return "success";
    }
}
