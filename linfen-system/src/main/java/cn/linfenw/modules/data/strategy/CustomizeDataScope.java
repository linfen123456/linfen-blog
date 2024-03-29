package cn.linfenw.modules.data.strategy;

import cn.linfenw.modules.data.enums.DataScopeTypeEnum;
import cn.linfenw.modules.sys.dto.RoleDTO;
import cn.linfenw.modules.sys.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname CustomizeDataScope
 * @Description 自定义
 * @Author linfen
 * @Date 2019-06-08 16:31
 * @Version 1.0
 */
@Component("4")
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysDeptService deptService;

    @Override
    public List<Integer> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
        List<Integer> roleDeptIds = roleDto.getRoleDepts();
        List<Integer> ids = new ArrayList<>();
        for (Integer deptId : roleDeptIds) {
            ids.addAll(deptService.selectDeptIds(deptId));
        }
        Set<Integer> set = new HashSet<>(ids);
        ids.clear();
        ids.addAll(set);
        return ids;
    }
}
