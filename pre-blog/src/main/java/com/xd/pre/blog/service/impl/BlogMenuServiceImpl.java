package com.xd.pre.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xd.pre.blog.domain.BlogMenu;
import com.xd.pre.blog.mapper.BlogMenuMapper;
import com.xd.pre.blog.service.IBlogMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.pre.blog.util.PreUtil;
import com.xd.pre.common.constant.MenuConstant;
import com.xd.pre.common.exception.PreBaseException;
import com.xd.pre.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前台菜单 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Service
public class BlogMenuServiceImpl extends ServiceImpl<BlogMenuMapper, BlogMenu> implements IBlogMenuService {

    @Override
    public R saveMenu(BlogMenu blogMenu) {
        // 菜单校验
        verifyForm(blogMenu);
        return R.ok(baseMapper.insert(blogMenu));
    }

    @Override
    public boolean updateMenuById(BlogMenu entity) {
        BlogMenu blogMenu = new BlogMenu();
        BeanUtils.copyProperties(entity, blogMenu);
        // 菜单校验
        verifyForm(blogMenu);
        return this.updateById(blogMenu);
    }

    @Override
    public List<BlogMenu> selectMenuTree(Integer uid) {

        LambdaQueryWrapper<BlogMenu> BlogMenuLambdaQueryWrapper = Wrappers.<BlogMenu>query().lambda();
        BlogMenuLambdaQueryWrapper.select(BlogMenu::getId, BlogMenu::getName, BlogMenu::getPerms, BlogMenu::getPath, BlogMenu::getParentId, BlogMenu::getComponent, BlogMenu::getIsFrame, BlogMenu::getIcon, BlogMenu::getSort, BlogMenu::getType, BlogMenu::getDelFlag);
        // 所有人有权限看到 只是没有权限操作而已 暂定这样

        List<BlogMenu> BlogMenus = new ArrayList<>();
        List<BlogMenu> menus = baseMapper.selectList(BlogMenuLambdaQueryWrapper);
        menus.forEach(menu -> {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                if (PreUtil.exists(BlogMenus, menu)) {
                    BlogMenus.add(menu);
                }
            }
        });
        BlogMenus.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));
        PreUtil.findChildren(BlogMenus, menus, 0);
        return BlogMenus;
    }

    @Override
    public BlogMenu getMenuById(Integer parentId) {
        return baseMapper.selectOne(Wrappers.<BlogMenu>lambdaQuery().select(BlogMenu::getType).eq(BlogMenu::getId, parentId));
    }


    @Override
    public R removeMenuById(Serializable id) {
        List<Integer> idList =
                this.list(Wrappers.<BlogMenu>query().lambda().eq(BlogMenu::getParentId, id)).stream().map(BlogMenu::getId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(idList)) {
            return R.error("菜单含有下级不能删除");
        }
        return R.ok(this.removeById(id));
    }

    /**
     * 验证菜单参数是否正确
     */
    private void verifyForm(BlogMenu menu) {
        //上级菜单类型
        int parentType = MenuConstant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            BlogMenu parentMenu = getMenuById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        //目录、菜单
        if (menu.getType() == MenuConstant.MenuType.CATALOG.getValue() ||
                menu.getType() == MenuConstant.MenuType.MENU.getValue()) {
            if (parentType != MenuConstant.MenuType.CATALOG.getValue()) {
                throw new PreBaseException("上级菜单只能为目录类型");
            }
            return;
        }
        //按钮
        if (menu.getType() == MenuConstant.MenuType.BUTTON.getValue()) {
            if (parentType != MenuConstant.MenuType.MENU.getValue()) {
                throw new PreBaseException("上级菜单只能为菜单类型");
            }
        }
    }
}
