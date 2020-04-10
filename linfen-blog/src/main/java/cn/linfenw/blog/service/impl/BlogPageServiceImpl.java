package cn.linfenw.blog.service.impl;

import cn.linfenw.blog.domain.BlogPage;
import cn.linfenw.blog.mapper.BlogPageMapper;
import cn.linfenw.blog.service.IBlogPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 页面 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-04-10
 */
@Service
public class BlogPageServiceImpl extends ServiceImpl<BlogPageMapper, BlogPage> implements IBlogPageService {

}
