package com.xd.pre.blog.domain;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基本信息
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogBasicInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 键名（英文）
     */
    private String keyname;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String content;

    /**
     * 描述
     */
    private String descriptions;

    /**
     * 是否必须（0否，1是）
     */
    private Integer must;

    private Integer tenantId;


}
