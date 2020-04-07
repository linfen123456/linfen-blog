package cn.linfenw.blog.service.impl;

import cn.linfenw.blog.domain.BlogAboutMe;
import cn.linfenw.blog.mapper.BlogAboutMeMapper;
import cn.linfenw.blog.service.IBlogAboutMeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关于我 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogAboutMeServiceImpl extends ServiceImpl<BlogAboutMeMapper, BlogAboutMe> implements IBlogAboutMeService {

}
