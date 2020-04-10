package cn.linfenw.blog.controller;


import cn.linfenw.blog.domain.BlogPage;
import cn.linfenw.blog.service.IBlogPageService;
import cn.linfenw.common.utils.R;
import cn.linfenw.log.annotation.SysOperaLog;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 页面 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/blog/page")
public class BlogPageController {

    @Autowired
    private IBlogPageService blogPageService;

    /**
     * 保存page
     *
     * @param blogPage
     * @return
     */
    @SysOperaLog(descrption = "保存page")
    @PostMapping
    @PreAuthorize("hasAuthority('blog:page:add')")
    public R insert(@RequestBody BlogPage blogPage) {
        return R.ok(blogPageService.save(blogPage));
    }

    /**
     * 查询page集合
     *
     * @param page
     * @param blogPage
     * @return
     */
    @GetMapping
    //@PreAuthorize("hasAuthority('blog:page:view')")
    public R getList(Page page, BlogPage blogPage) {
        return R.ok(blogPageService.page(page, Wrappers.query(blogPage).lambda().orderByDesc(BlogPage::getCreateTime)));
    }

    /**
     * 查询所有分类集合
     *
     * @param page
     * @param blogPage
     * @return
     */
    @GetMapping("/all")
    //@PreAuthorize("hasAuthority('blog:category:view')")
    public R getAllList(Page page, BlogPage blogPage) {
        return R.ok(blogPageService.list(Wrappers.query(blogPage)));
    }

    /**
     * 通过路径查询
     *
     * @param page
     * @param path
     * @return
     */
    @GetMapping("/link")
    //@PreAuthorize("hasAuthority('blog:category:view')")
    public R getOneByLink(@Param("path") String path) {
        Wrapper<BlogPage> wrapper=Wrappers.<BlogPage>lambdaQuery().eq(BlogPage::getLink,path);
        return R.ok(blogPageService.getOne(wrapper));
    }

    /**
     * 更新page
     *
     * @param blogPage
     * @return
     */
    @SysOperaLog(descrption = "更新page")
    @PutMapping
    @PreAuthorize("hasAuthority('blog:page:update')")
    public R update(@RequestBody BlogPage blogPage) {
        return R.ok(blogPageService.updateById(blogPage));
    }

    /**
     * 根据id查询页面
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R byId(@PathVariable("id") Integer id) {
        return R.ok(blogPageService.getById(id));
    }

    /**
     * 根据id删除page
     *
     * @param id
     * @return
     */
    @SysOperaLog(descrption = "根据id删除page")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('blog:page:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(blogPageService.removeById(id));
    }
}

