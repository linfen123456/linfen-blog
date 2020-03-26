package com.xd.pre.blog.service.impl;

import com.xd.pre.blog.domain.BlogDiscuss;
import com.xd.pre.blog.mapper.BlogDiscussMapper;
import com.xd.pre.blog.service.IBlogDiscussService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public List<BlogDiscuss> getRootBlogDiscussByArticleId(Integer articleId) {
        return baseMapper.getRootBlogDiscussByArticleId(articleId);
    }
}
