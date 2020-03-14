package com.xd.pre.modules.data.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xd.pre.security.PreSecurityUser;
import com.xd.pre.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname PrexMetaObjectHandler
 * @Description  字段自动填充器
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-11-13 16:25
 * @Version 1.0
 */
@Slf4j
@Component
public class PrexMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        PreSecurityUser user = SecurityUtil.getUser();
        log.info("start insert fill ....");
        //避免使用metaObject.setValue()
        if (metaObject.hasGetter("delFlag") ) {
            this.setFieldValByName("delFlag", "0", metaObject);
        }
        if (metaObject.hasGetter("createTime") ) {
            this.setFieldValByName("createTime", new Date(), metaObject);
            this.setFieldValByName("userId", user.getUserId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        PreSecurityUser user = SecurityUtil.getUser();

        if (metaObject.hasGetter("operator") ) {
            this.setFieldValByName("operator", user.getUsername(), metaObject);        }

        if (metaObject.hasGetter("updateTime") ) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }

    }
}
