package cn.linfenw.modules.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.linfenw.modules.security.social.PreConnectionData;
import cn.linfenw.modules.sys.domain.SysSocial;
import cn.linfenw.modules.sys.domain.SysUser;
import cn.linfenw.modules.sys.service.ISysSocialService;
import cn.linfenw.modules.sys.service.ISysUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.linfenw.common.utils.R;
import cn.linfenw.log.annotation.SysOperaLog;
import cn.linfenw.modules.security.social.SocialRedisHelper;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @Classname SysSocialController
 * @Description 社交登录
 * @Author linfen
 * @Date 2019-07-17 16:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/social")
public class SysSocialController {

    @Autowired
    private SocialRedisHelper socialRedisHelper;
    @Autowired
    private ISysSocialService socialService;



    /**
     * 社交查询列表
     *
     * @param page
     * @return
     */
    @PreAuthorize("hasAuthority('sys:social:view')")
    @GetMapping
    public R selectSocial(Page page, SysSocial sysSocial) {
        return R.ok(socialService.selectSocialList(page,sysSocial));
    }

    /**
     * 社交登录解绑
     * @param userId
     * @param providerId
     * @return
     */
    @SysOperaLog(descrption = "解绑社交账号")
    @PostMapping("/untied")
    @PreAuthorize("hasAuthority('sys:social:untied')")
    public R untied(Integer userId, String providerId) {
        //将业务系统的用户与社交用户绑定
        socialRedisHelper.doPostSignDown(userId, providerId);
        return R.ok();
    }



}
