package cn.linfenw.security.util;

import com.alibaba.fastjson.JSON;
import cn.linfenw.common.exception.PreBaseException;
import cn.linfenw.common.utils.R;
import cn.linfenw.security.LoginType;
import cn.linfenw.security.PreSecurityUser;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname SecurityUtil
 * @Description 安全服务工具类
 * @Author linfen
 * @Date 2019-05-08 10:12
 * @Version 1.0
 */
@UtilityClass
public class SecurityUtil {

    public void writeJavaScript(R r, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(r));
        printWriter.flush();
    }

    /**
     * 获取Authentication
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * @Author 李号东
     * @Description 获取用户
     * @Date 11:29 2019-05-10
     **/
    public PreSecurityUser getUser(){
        try {
            return (PreSecurityUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new PreBaseException("登录状态过期", HttpStatus.UNAUTHORIZED.value());
        }
    }


    /**
     * @Author 李号东
     * @Description 获取用户
     * @Date 11:29 2019-05-10
     **/
    public PreSecurityUser getDefaultUser(){
        try {
            return (PreSecurityUser) getAuthentication().getPrincipal();
        } catch (Exception e) {

            return new PreSecurityUser(-1,"未登录","000",null, LoginType.normal);
        }
    }
}
