package cn.linfenw.blog.mapper;

import cn.linfenw.blog.domain.BlogLink;
import cn.linfenw.blog.dto.LinkDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

    Integer updateVisibleById(BlogLink blogLink);
}
