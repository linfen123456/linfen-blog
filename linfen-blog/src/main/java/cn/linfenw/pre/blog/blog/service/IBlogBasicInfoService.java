package cn.linfenw.pre.blog.blog.service;

import cn.linfenw.pre.blog.blog.domain.BlogBasicInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 基本信息 服务类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
public interface IBlogBasicInfoService extends IService<BlogBasicInfo> {

    Map<String, Object> pageKeyValueList(Page page);
}
