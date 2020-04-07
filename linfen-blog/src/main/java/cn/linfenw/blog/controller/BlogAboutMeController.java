package cn.linfenw.blog.controller;


import cn.linfenw.blog.domain.BlogAboutMe;
import cn.linfenw.blog.service.IBlogAboutMeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.linfenw.common.utils.R;
import cn.linfenw.log.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 关于我 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/blog/about-me")
public class BlogAboutMeController {
    @Autowired
    private IBlogAboutMeService blogAboutMeService;

    /**
     * 保存关于我
     *
     * @param blogAboutMe
     * @return
     */
    @SysOperaLog(descrption = "保存关于我")
    @PostMapping
    @PreAuthorize("hasAuthority('blog:about_me:add')")
    public R insert(@RequestBody BlogAboutMe blogAboutMe) {
        return R.ok(blogAboutMeService.save(blogAboutMe));
    }

    /**
     * 查询关于我集合
     *
     * @param page
     * @param blogAboutMe
     * @return
     */
    @GetMapping
//    @PreAuthorize("hasAuthority('blog:about_me:view')")
    public R getList(Page page,  BlogAboutMe blogAboutMe) {
        return R.ok(blogAboutMeService.page(page, Wrappers.query(blogAboutMe).lambda().orderByDesc(BlogAboutMe::getCreateTime)));
    }

    /**
     * 查询所有分类集合
     *
     * @param page
     * @param blogAboutMe
     * @return
     */
    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('blog:category:view')")
    public R getAllList(Page page,  BlogAboutMe blogAboutMe) {
        return R.ok(blogAboutMeService.list(Wrappers.query(blogAboutMe)));
    }

    /**
     * 更新关于我
     *
     * @param blogAboutMe
     * @return
     */
    @SysOperaLog(descrption = "更新关于我")
    @PutMapping
    @PreAuthorize("hasAuthority('blog:about_me:update')")
    public R update(@RequestBody  BlogAboutMe blogAboutMe) {
        return R.ok(blogAboutMeService.updateById(blogAboutMe));
    }

    /**
     * 根据id删除关于我
     *
     * @param id
     * @return
     */
    @SysOperaLog(descrption = "根据id删除关于我")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('blog:about_me:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(blogAboutMeService.removeById(id));
    }
}

