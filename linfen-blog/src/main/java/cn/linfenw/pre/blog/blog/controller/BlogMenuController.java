package cn.linfenw.pre.blog.blog.controller;


import cn.linfenw.pre.blog.blog.domain.BlogMenu;
import cn.linfenw.pre.blog.blog.service.IBlogMenuService;
import cn.linfenw.pre.blog.blog.util.PreUtil;
import cn.linfenw.pre.common.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前台菜单 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/blog/menu")
public class BlogMenuController {
    @Autowired
    private IBlogMenuService blogMenuService;
    /**
     * 添加菜单
     *
     * @param blogMenu
     * @return
     */
    @PreAuthorize("hasAuthority('blog:menu:add')")
    @SysOperaLog(descrption = "添加菜单")
    @PostMapping
    public R save(@RequestBody BlogMenu blogMenu) {
        return blogMenuService.saveMenu(blogMenu);
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @GetMapping
    public R getList() {
        return R.ok(blogMenuService.selectMenuTree(0));
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @GetMapping("/tree")
    public R getMenus() {
        return R.ok(PreUtil.buildMenus(blogMenuService.selectMenuTree(0)));
    }

    /**
     * 修改菜单
     *
     * @param blogMenu
     * @return
     */
    @PreAuthorize("hasAuthority('blog:menu:update')")
    @SysOperaLog(descrption = "修改菜单")
    @PutMapping
    public R updateMenu(@RequestBody BlogMenu blogMenu) {
        return R.ok(blogMenuService.updateMenuById(blogMenu));
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('blog:menu:delete')")
    @SysOperaLog(descrption = "删除菜单")
    @DeleteMapping("/{id}")
    public R deleteMenu(@PathVariable("id") Integer id) {
        return blogMenuService.removeMenuById(id);
    }

}

