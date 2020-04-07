package cn.linfenw.modules.sys.service;

import cn.linfenw.modules.sys.domain.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    List<Integer> getMenuIdByUserId(Integer userId);


}
