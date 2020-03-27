package com.xd.pre.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pre.blog.dto.BlogArticleDTO;
import com.xd.pre.blog.dto.PigeonholeDTO;
import com.xd.pre.blog.vo.BlogArticleVo;

import java.util.List;

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
}
