package com.xd.pre.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogBasicInfo;
import com.xd.pre.blog.domain.BlogDiscuss;
import com.xd.pre.blog.mapper.BlogBasicInfoMapper;
import com.xd.pre.blog.service.IBlogBasicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
