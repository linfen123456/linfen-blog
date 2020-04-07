package cn.linfenw.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogDiscuss extends BaseDomain implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 父评论ID
     */
    private Long parentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 类型（０登录用户，１匿名用户）
     */
    private Integer type;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网站
     */
    private String website;

    private Integer tenantId;

    /**
     * 头像 非数据库字段
     */
    @TableField(exist = false)
    private String avatar;

    /**
     * 用户昵称 非数据库字段
     */
    @TableField(exist = false)
    private String nickname1;


    /**
     * 子评论
     */
    @TableField(exist = false)
    private List<BlogDiscuss> childrens;

}
