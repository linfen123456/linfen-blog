package cn.linfenw.modules.data.strategy;

import cn.linfenw.modules.data.enums.DataScopeTypeEnum;
import cn.linfenw.modules.sys.dto.RoleDTO;
import cn.linfenw.modules.sys.service.ISysDeptService;
import cn.linfenw.modules.sys.service.ISysUserService;
import cn.linfenw.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname ThisLevelChildenDataScope
 * @Description 本级以及子级
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-06-08 16:30
 * @Version 1.0
 */
@Component("3")
public class ThisLevelChildenDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;


    @Override
    public List<Integer> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
        Integer deptId = userService.findByUserInfoName(SecurityUtil.getUser().getUsername()).getDeptId();
        return deptService.selectDeptIds(deptId);
    }
}
