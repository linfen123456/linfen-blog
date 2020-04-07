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
 * 文章下载
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogDownload extends BaseDomain implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 标题
     */
    private String title;

    /**
     * 原来文件名称
     */
    private String originName;

    /**
     * 描述
     */
    private String descriptions;

    /**
     * 大小
     */
    private Double fileSize;

    /**
     * 所属类型（0文档，1代码，2工具，3其他）
     */
    private Integer type;

    /**
     * 地址
     */
    private String url;

    /**
     * 下载量
     */
    private Long downloads;

    private Integer tenantId;


}
