package com.xd.pre.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogLink;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pre.blog.dto.LinkDTO;

import java.util.Map;

/**
 * <p>
 * 友联网站 服务类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
public interface IBlogLinkService extends IService<BlogLink> {

    IPage<LinkDTO> orderByAll(Page page, BlogLink blogLink);

    Integer updateVisibleById(BlogLink blogLink);
}
