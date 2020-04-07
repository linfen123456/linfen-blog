package cn.linfenw.blog.service.impl;

import cn.linfenw.blog.domain.BlogDownload;
import cn.linfenw.blog.mapper.BlogDownloadMapper;
import cn.linfenw.blog.service.IBlogDownloadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章下载 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogDownloadServiceImpl extends ServiceImpl<BlogDownloadMapper, BlogDownload> implements IBlogDownloadService {

}
