package com.xd.pre.blog.service.impl;

import com.xd.pre.blog.domain.BlogArticle;
import com.xd.pre.blog.mapper.BlogArticleMapper;
import com.xd.pre.blog.service.IBlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
