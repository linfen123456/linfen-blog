package cn.linfenw.blog.service.impl;

import cn.linfenw.blog.domain.BlogCategory;
import cn.linfenw.blog.mapper.BlogCategoryMapper;
import cn.linfenw.blog.service.IBlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements IBlogCategoryService {

}
