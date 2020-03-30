package com.xd.pre.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogBasicInfo;
import com.xd.pre.blog.service.IBlogBasicInfoService;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.invoke.util.Wrapper;

/**
 * <p>
 * 基本信息 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/blog/basic-info")
public class BlogBasicInfoController {
    @Autowired
    public IBlogBasicInfoService basicInfoService;

    /**
     * 保存基本信息
     *
     * @param  blogBasicInfo
     * @return
     */
    @SysOperaLog(descrption = "保存基本信息")
    @PostMapping
    @PreAuthorize("hasAuthority('blog:basic-info:add')")
    public R insert(@RequestBody BlogBasicInfo blogBasicInfo) {
        return R.ok(basicInfoService.save( blogBasicInfo));
    }

    /**
     * 查询基本信息集合
     *
     * @param page
     * @param  blogBasicInfo
     * @return
     */
    @SysOperaLog(descrption = "查询基本信息集合")
    @GetMapping
//    @PreAuthorize("hasAuthority('blog:basic-info:view')")
    public R getList(Page page,  BlogBasicInfo blogBasicInfo) {
        //进行模糊匹配
        QueryWrapper<BlogBasicInfo> query = Wrappers.query(blogBasicInfo);
        if (blogBasicInfo.getKeyname()!=null) {
            query.like("keyname",blogBasicInfo.getKeyname());
            blogBasicInfo.setKeyname(null);
        }
        if (blogBasicInfo.getName()!=null) {
            query.like("name", blogBasicInfo.getName());
            blogBasicInfo.setName(null);
        }
        query.lambda().orderByDesc(BlogBasicInfo::getCreateTime);
        return R.ok(basicInfoService.page(page, query));
    }

    /**
     * 查询基本信息集合
     *
     * @param page
     * @return
     */
    @Cacheable(cacheNames = "keyValueBasicInfo" ,key = "keyValueBasicInfo")
    @GetMapping("keyValueList")
//    @PreAuthorize("hasAuthority('blog:basic-info:view')")
    public R getKeyValueList(Page page) {
        return R.ok(basicInfoService.pageKeyValueList(page));
    }

    /**
     * 更新基本信息
     *
     * @param  blogBasicInfo
     * @return
     */
    @SysOperaLog(descrption = "更新基本信息")
    @PutMapping
    @PreAuthorize("hasAuthority('blog:basic-info:update')")
    public R update(@RequestBody  BlogBasicInfo blogBasicInfo) {
        UpdateWrapper<BlogBasicInfo> wrapper = Wrappers.update();
        wrapper.lambda().eq(BlogBasicInfo ::getKeyname, blogBasicInfo.getKeyname());
        return R.ok(basicInfoService.update(blogBasicInfo,wrapper ));
    }

    /**
     * 根据id删除基本信息
     *
     * @param id
     * @return
     */
    @SysOperaLog(descrption = "根据id删除基本信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('blog:basic-info:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(basicInfoService.removeById(id));
    }
}

