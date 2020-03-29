package com.xd.pre.blog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.blog.dto.LinkDTO;

import java.util.Map;

/**
 * <p>
 * 友联网站 Mapper 接口
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
public interface BlogLinkMapper extends BaseMapper<BlogLink> {

    IPage<LinkDTO> orderByAll(Page page, BlogLink blogLink);
}
