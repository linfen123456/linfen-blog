package cn.linfenw.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 页面
 * </p>
 *
 * @author linfen
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogPage extends BaseDomain implements Serializable{

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
     * 内容
     */
    private String content;

    /**
     * 短地址
     */
    private String link;

    /**
     * logo
     */
    private String logo;

    /**
     * 是否开启评论（0开启1关闭）
     */
    private Integer openDiscuss;

    /**
     * 草稿（0否1是）
     */
    private String isDraft;

    /**
     * 状态（0开放1私有）
     */
    private String status;

    /**
     * 租户ID
     */
    private Integer tenantId;
}
