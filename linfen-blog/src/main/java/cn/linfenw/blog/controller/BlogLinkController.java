package cn.linfenw.blog.controller;


import cn.linfenw.blog.domain.BlogLink;
import cn.linfenw.blog.service.IBlogLinkService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.linfenw.common.utils.R;
import cn.linfenw.log.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 友联网站 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/blog/link")
public class BlogLinkController {
    @Autowired
    private IBlogLinkService blogLinkService;

    /**
     * 保存友联
     *
     * @param blogLink
     * @return
     */
    @SysOperaLog(descrption = "保存友联")
    @PostMapping
    @PreAuthorize("hasAuthority('blog:link:add')")
    public R insert(@RequestBody BlogLink blogLink) {
        return R.ok(blogLinkService.save(blogLink));
    }

    /**
     * 查询友联集合
     *
     * @param page
     * @param blogLink
     * @return
     */
    @GetMapping
//    @PreAuthorize("hasAuthority('blog:link:view')")
    public R getList(Page page, BlogLink blogLink) {
        return R.ok(blogLinkService.page(page, Wrappers.query(blogLink).lambda().orderByDesc(BlogLink::getCreateTime)));
    }

    /**
     * 查询所有分类集合
     *
     * @param page
     * @param blogLink
     * @return
     */
    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('blog:category:view')")
    public R getAllList(Page page, BlogLink blogLink) {
        return R.ok(blogLinkService.list(Wrappers.query(blogLink)));
    }

    /**
     * 查询所有分类集合
     *
     * @param page
     * @param blogLink
     * @return
     */
    @GetMapping("/orderByAll")
//    @PreAuthorize("hasAuthority('blog:category:view')")
    public R getOrderByAllList(Page page, BlogLink blogLink) {
        return R.ok(blogLinkService.orderByAll(page,blogLink));
    }

    /**
     * 更新友联
     *
     * @param blogLink
     * @return
     */
    @SysOperaLog(descrption = "更新友联")
    @PutMapping
    @PreAuthorize("hasAuthority('blog:link:update')")
    public R update(@RequestBody BlogLink blogLink) {
        return R.ok(blogLinkService.updateById(blogLink));
    }

    /**
     * 更新友联访问人数
     *
     * @param blogLink
     * @return
     */
    @SysOperaLog(descrption = "更新访问量友联")
    @PutMapping("updatevisible")
    public R updateVisible(@RequestBody BlogLink blogLink) {
        return R.ok(blogLinkService.updateVisibleById(blogLink));
    }

    /**
     * 根据id删除友联
     *
     * @param id
     * @return
     */
    @SysOperaLog(descrption = "根据id删除友联")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('blog:link:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(blogLinkService.removeById(id));
    }
}

