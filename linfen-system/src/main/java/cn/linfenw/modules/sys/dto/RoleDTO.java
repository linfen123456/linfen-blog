package cn.linfenw.modules.sys.dto;

import cn.linfenw.modules.sys.domain.SysRoleMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Classname UserDTO
 * @Description 角色Dto
 * @Author linfen
 * @Date 2019-04-23 21:26
 * @Version 1.0
 */
@Setter
@Getter
public class RoleDTO {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String delFlag;
    private int dsType;
    List<SysRoleMenu> roleMenus;
    List<Integer> roleDepts;



}
