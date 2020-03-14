package com.xd.pre.blog.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogTag;
import com.xd.pre.blog.service.IBlogTagService;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController {
    @Autowired
    private IBlogTagService blogTagService;

    /**
     * 保存标签
     *
     * @param blogTag
     * @return
     */
    @SysOperaLog(descrption = "保存标签")
    @PostMapping
    @PreAuthorize("hasAuthority('blog:tag:add')")
    public R insert(@RequestBody BlogTag blogTag) {
        return R.ok(blogTagService.save(blogTag));
    }

    /**
     * 查询标签集合
     *
     * @param page
     * @param blogTag
     * @return
     */
    @SysOperaLog(descrption = "查询标签集合")
    @GetMapping
//    @PreAuthorize("hasAuthority('blog:tag:view')")
    public R getList(Page page, BlogTag blogTag) {
        return R.ok(blogTagService.page(page, Wrappers.query(blogTag).lambda().orderByDesc(BlogTag::getCreateTime)));
    }

    /**
     * 更新标签
     *
     * @param blogTag
     * @return
     */
    @SysOperaLog(descrption = "更新标签")
    @PutMapping
    @PreAuthorize("hasAuthority('blog:tag:update')")
    public R update(@RequestBody BlogTag blogTag) {
        return R.ok(blogTagService.updateById(blogTag));
    }

    /**
     * 根据id删除标签
     *
     * @param id
     * @return
     */
    @SysOperaLog(descrption = "根据id删除标签")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('blog:tag:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(blogTagService.removeById(id));
    }

}

