package cn.linfenw.blog.service.impl;

import cn.linfenw.blog.domain.BlogTag;
import cn.linfenw.blog.mapper.BlogTagMapper;
import cn.linfenw.blog.service.IBlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-14
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements IBlogTagService {

}
