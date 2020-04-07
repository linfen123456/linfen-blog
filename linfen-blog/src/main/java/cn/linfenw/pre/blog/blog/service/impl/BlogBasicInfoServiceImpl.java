package cn.linfenw.pre.blog.blog.service.impl;

import cn.linfenw.pre.blog.blog.domain.BlogBasicInfo;
import cn.linfenw.pre.blog.blog.mapper.BlogBasicInfoMapper;
import cn.linfenw.pre.blog.blog.service.IBlogBasicInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基本信息 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogBasicInfoServiceImpl extends ServiceImpl<BlogBasicInfoMapper, BlogBasicInfo> implements IBlogBasicInfoService {

    @Override
    public Map<String, Object> pageKeyValueList(Page page) {
        List<BlogBasicInfo> blogBasicInfos = baseMapper.selectList(null);
        Map<String, Object> map = new HashMap<>();
        for (BlogBasicInfo b :blogBasicInfos) {
            if (b.getKeyname()!=null)
            map.put(b.getKeyname(), b);
        }
        return map;
    }
}
