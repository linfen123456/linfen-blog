package cn.linfenw.blog.service;

import cn.linfenw.blog.domain.BlogMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.linfenw.common.utils.R;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 前台菜单 服务类
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
public interface IBlogMenuService extends IService<BlogMenu> {
    /**
     * 删除菜单信息
     * @param blogMenu
     * @return
     */
    R saveMenu(BlogMenu blogMenu);

    /**
     * 删除菜单信息
     * @param id
     * @return
     */
    R removeMenuById(Serializable id);

    /**
     * 更新菜单信息
     * @param entity
     * @return
     */
    boolean updateMenuById(BlogMenu entity);

    /**
     * 根据用户id查找菜单树
     * @return
     */
    List<BlogMenu> selectMenuTree(Integer parentId);

    /**
     * @Author linfen
     * @Description 根据父id查询菜单
     * @Date 18:43 2020-03-14
     **/
    BlogMenu getMenuById(Integer parentId);

}
