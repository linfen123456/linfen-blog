package cn.linfenw.blog.mapper;

import cn.linfenw.blog.domain.BlogDiscuss;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
public interface BlogDiscussMapper extends BaseMapper<BlogDiscuss> {

    public List<BlogDiscuss> getRootBlogDiscussByArticleId(@Param("articleId") Integer articleId);        //返回根菜单

    //根据父一级菜单，返回所有子菜单
    public List<BlogDiscuss> findDiscussByParentId(@Param("parentId") Integer parentId);
}
