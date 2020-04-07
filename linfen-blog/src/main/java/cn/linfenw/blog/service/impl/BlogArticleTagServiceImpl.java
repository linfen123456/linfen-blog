package cn.linfenw.blog.service.impl;

import cn.linfenw.blog.domain.BlogArticleTag;
import cn.linfenw.blog.mapper.BlogArticleTagMapper;
import cn.linfenw.blog.service.IBlogArticleTagService;
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
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements IBlogArticleTagService {

}
