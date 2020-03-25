package com.xd.pre.blog.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogCategory;
import com.xd.pre.blog.service.IBlogCategoryService;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 分类 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/blog/category")
public class BlogCategoryController {
    @Autowired
    private IBlogCategoryService blogCategoryService;

    /**
     * 保存分类
     *
     * @param blogCategory
     * @return
     */
    @SysOperaLog(descrption = "保存分类")
    @PostMapping
    @PreAuthorize("hasAuthority('blog:category:add')")
    public R insert(@RequestBody BlogCategory blogCategory) {
        return R.ok(blogCategoryService.save(blogCategory));
    }

    /**
     * 查询分类集合
     *
     * @param page
     * @param blogCategory
     * @return
     */
    @SysOperaLog(descrption = "查询分类集合")
    @GetMapping
//    @PreAuthorize("hasAuthority('blog:category:view')")
    public R getList(Page page, BlogCategory blogCategory) {
        return R.ok(blogCategoryService.page(page, Wrappers.query(blogCategory).lambda().orderByDesc(BlogCategory::getCreateTime)));
    }

    /**
     * 查询所有分类集合
     *
     * @param page
     * @param blogCategory
     * @return
     */
    @SysOperaLog(descrption = "查询所有分类集合")
    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('blog:category:view')")
    public R getAllList(Page page, BlogCategory blogCategory) {
        return R.ok(blogCategoryService.list(Wrappers.query(blogCategory)));
    }

    /**
     * 更新分类
     *
     * @param blogCategory
     * @return
     */
    @SysOperaLog(descrption = "更新分类")
    @PutMapping
    @PreAuthorize("hasAuthority('blog:category:update')")
    public R update(@RequestBody BlogCategory blogCategory) {
        return R.ok(blogCategoryService.updateById(blogCategory));
    }

    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    @SysOperaLog(descrption = "根据id删除分类")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('blog:category:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(blogCategoryService.removeById(id));
    }

}

