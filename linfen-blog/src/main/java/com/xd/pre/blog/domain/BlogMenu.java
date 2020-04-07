package com.xd.pre.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 前台菜单
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogMenu extends BaseDomain implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限标识
     */
    private String perms;

    /**
     * 前端跳转URL
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型 （类型   0：目录   1：菜单   2：按钮）
     */
    private Integer type;

    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    private String delFlag;

    /**
     * 是否为外链
     */
    private Boolean isFrame;

    /**
     * 租户ID
     */
    private Integer tenantId;

    /* 非数据库字段
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 非数据库字段
     * 菜单等级
     */
    @TableField(exist = false)
    private Integer level;

    /**
     * 非数据库字段
     * 子菜单
     */
    @TableField(exist = false)
    private List<BlogMenu> children;
}
