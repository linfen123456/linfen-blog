package com.xd.pre.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogArticle extends BaseDomain implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String abstracts;

    /**
     * 内容
     */
    private String content;

    /**
     * 封面
     */
    private String cover;

    /**
     * 访问量
     */
    private Long views;

    /**
     * 评论量
     */
    private Long discuss;

    /**
     * 开启评论（0开启，1关闭）
     */
    private String openDiscuss;

    /**
     * 类型（0原创，1转载，2翻译）
     */
    private String type;

    /**
     * 是否置顶（0否，1是）
     */
    private String istop;

    /**
     * 文章分类ID
     */
    private Integer categoryId;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 状态（0公开1私有）
     */
    private String isView;


    private Integer tenantId;


}
