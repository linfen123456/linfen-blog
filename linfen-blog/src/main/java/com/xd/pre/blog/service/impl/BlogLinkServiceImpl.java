package com.xd.pre.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogLink;
import com.xd.pre.blog.dto.LinkDTO;
import com.xd.pre.blog.mapper.BlogLinkMapper;
import com.xd.pre.blog.service.IBlogLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 友联网站 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogLinkServiceImpl extends ServiceImpl<BlogLinkMapper, BlogLink> implements IBlogLinkService {

    @Override
    public IPage<LinkDTO> orderByAll(Page page, BlogLink blogLink) {
        return baseMapper.orderByAll(page,blogLink);
    }

    @Override
    public Integer updateVisibleById(BlogLink blogLink) {
        return baseMapper.updateVisibleById(blogLink);
    }
}
