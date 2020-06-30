package com.sundeinfo.kd.zs.dao.custom;

import com.sundeinfo.kd.zs.dto.sys.UserPermissionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionCustomMapper {
    List<UserPermissionDTO> selectRolePermission(@Param("roleIds") List<Integer> roleIds );


}


