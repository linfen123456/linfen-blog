package com.xd.pre.blog.service.impl;

import com.xd.pre.blog.domain.BlogArticle;
import com.xd.pre.blog.domain.BlogDiscuss;
import com.xd.pre.blog.mapper.BlogDiscussMapper;
import com.xd.pre.blog.service.IBlogArticleService;
import com.xd.pre.blog.service.IBlogDiscussService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogDiscussServiceImpl extends ServiceImpl<BlogDiscussMapper, BlogDiscuss> implements IBlogDiscussService {
    @Autowired
    private IBlogArticleService articleService;

    @Override
    public List<BlogDiscuss> getRootBlogDiscussByArticleId(Integer articleId) {
        return baseMapper.getRootBlogDiscussByArticleId(articleId);
    }

    @Transactional
    @Override
    public Integer saveBlogDiscuss(BlogDiscuss blogDiscuss) {
        //评论加1
        BlogArticle blogArticle = articleService.getById(blogDiscuss.getArticleId());
        long count = blogArticle.getDiscuss()+1;
        blogArticle.setDiscuss(count);
        articleService.updateById(blogArticle);

        return baseMapper.insert(blogDiscuss);
    }
}
