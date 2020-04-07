package cn.linfenw.modules.sys.service.impl;

import cn.linfenw.modules.sys.domain.SysRoleMenu;
import cn.linfenw.modules.sys.mapper.SysRoleMenuMapper;
import cn.linfenw.modules.sys.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2019-04-21
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public List<Integer> getMenuIdByUserId(Integer userId) {
        return baseMapper.getMenuIdByUserId(userId);
    }
}
