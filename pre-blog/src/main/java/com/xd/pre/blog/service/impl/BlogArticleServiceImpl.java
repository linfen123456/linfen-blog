package com.xd.pre.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogArticle;
import com.xd.pre.blog.domain.BlogArticleTag;
import com.xd.pre.blog.dto.BlogArticleDTO;
import com.xd.pre.blog.mapper.BlogArticleMapper;
import com.xd.pre.blog.service.IBlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.pre.blog.service.IBlogArticleTagService;
import com.xd.pre.blog.service.IBlogTagService;
import com.xd.pre.blog.vo.BlogArticleVo;
import com.xd.pre.common.utils.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

    @Autowired
    private IBlogArticleTagService blogArticleTagService;

    @Transactional
    @Override
    public boolean saveArticle(BlogArticleVo blogArticle) {
        List<BlogArticleTag> tasList = new ArrayList<>();

        //保存文章
        BlogArticle article = new BlogArticle();
        BeanUtils.copyProperties(blogArticle,article);
        baseMapper.insert(article);

        //保存标签
        for (Long tagId: blogArticle.getTags()) {
            BlogArticleTag tag = new BlogArticleTag();
            tag.setTagId(tagId);
            tag.setArticleId(article.getId());
            tasList.add(tag);
        }
        return blogArticleTagService.saveBatch(tasList);
    }

    @Override
    public BlogArticleVo queryById(Integer id) {
        BlogArticle article = baseMapper.selectById(id);
        BlogArticleVo articleVo = new BlogArticleVo();

        BeanUtils.copyProperties(article,articleVo);
        List<BlogArticleTag> blogArticleTags = blogArticleTagService.list(new QueryWrapper<BlogArticleTag>()
                .eq("article_id", article.getId()));
        List<Long> tags = new ArrayList<>();
        blogArticleTags.forEach(blogArticleTag -> tags.add(blogArticleTag.getTagId()));
        articleVo.setTags(tags);
        return articleVo;
    }

    @Override
    public IPage<BlogArticleDTO> pageList(Page page, Wrapper queryWrapper) {
        return baseMapper.pageList(page,queryWrapper);
    }
}
