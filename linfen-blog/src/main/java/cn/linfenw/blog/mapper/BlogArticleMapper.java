package cn.linfenw.blog.mapper;

import cn.linfenw.blog.dto.BlogArticleDTO;
import cn.linfenw.blog.dto.PigeonholeDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.linfenw.blog.domain.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    IPage<BlogArticleDTO> pageList(@Param("page") Page page, @Param(Constants.WRAPPER) BlogArticle blogArticle);

    BlogArticleDTO selectByIds(Integer id);

    IPage<PigeonholeDTO> pagePigeonhole(Page page);

    IPage<BlogArticleDTO> pageListByTagId(Page page,@Param("tagId") Integer tagId);

    IPage<BlogArticleDTO> pageArticleByName(Page page,@Param("title") String title,@Param("userId") Integer userId);
}
