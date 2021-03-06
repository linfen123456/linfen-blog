package cn.linfenw.modules.sys.controller;


import cn.linfenw.log.annotation.SysOperaLog;
import cn.linfenw.modules.sys.domain.SysMenu;
import cn.linfenw.modules.sys.dto.MenuDTO;
import cn.linfenw.modules.sys.service.ISysMenuService;
import cn.linfenw.modules.sys.util.PreUtil;
import cn.linfenw.security.PreSecurityUser;
import cn.linfenw.security.util.SecurityUtil;
import cn.linfenw.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2019-04-21
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @SysOperaLog(descrption = "添加菜单")
    @PostMapping
    public R save(@RequestBody SysMenu menu) {
        return R.ok(menuService.save(menu));
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping
    public R getMenuTree() {
        PreSecurityUser securityUser = SecurityUtil.getUser();
        return R.ok(menuService.selectMenuTree(securityUser.getUserId()));
    }


    /**
     * 获取所有菜单
     *
     * @return
     */
    @GetMapping("/getMenus")
    public R getMenus() {
        return R.ok(menuService.selectMenuTree(0));
    }

    /**
     * 修改菜单
     *
     * @param menuDto
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:update')")
    @SysOperaLog(descrption = "修改菜单")
    @PutMapping
    public R updateMenu(@RequestBody MenuDTO menuDto) {
        return R.ok(menuService.updateMenuById(menuDto));
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @SysOperaLog(descrption = "删除菜单")
    @DeleteMapping("/{id}")
    public R deleteMenu(@PathVariable("id") Integer id) {
        return menuService.removeMenuById(id);
    }

    /**
     * 获取路由
     *
     * @return
     */
    @GetMapping("/getRouters")
    public R getRouters() {
        PreSecurityUser securityUser = SecurityUtil.getUser();
//        List<MenuVo> menuVos = PreUtil.buildMenus(menuService.selectMenuTree(securityUser.getUserId()));
        return R.ok(PreUtil.buildMenus(menuService.selectMenuTree(securityUser.getUserId())));
    }

}

