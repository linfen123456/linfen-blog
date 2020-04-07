package cn.linfenw.pre.blog.blog.service;

import cn.linfenw.pre.blog.blog.domain.BlogLink;
import cn.linfenw.pre.blog.blog.dto.LinkDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

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
