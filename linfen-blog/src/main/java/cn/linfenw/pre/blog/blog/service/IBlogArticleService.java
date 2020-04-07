package cn.linfenw.pre.blog.blog.service;

import cn.linfenw.pre.blog.blog.domain.BlogArticle;
import cn.linfenw.pre.blog.blog.dto.BlogArticleDTO;
import cn.linfenw.pre.blog.blog.dto.PigeonholeDTO;
import cn.linfenw.pre.blog.blog.vo.BlogArticleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
public interface IBlogArticleService extends IService<BlogArticle> {

    boolean saveArticle(BlogArticleVo blogArticle);

    BlogArticleVo queryById(Integer id);

    IPage<BlogArticleDTO> pageList(Page page, BlogArticle blogArticle);

    BlogArticleDTO selectById(Integer id);

    IPage<PigeonholeDTO> pagePigeonhole(Page page);

    IPage<BlogArticleDTO> pageListByTagId(Page page, Integer tagId);

    IPage<BlogArticleDTO> pageArticleByName(Page page, String title, Integer userId);

    Integer deleteByArticleId(Integer id);
}
