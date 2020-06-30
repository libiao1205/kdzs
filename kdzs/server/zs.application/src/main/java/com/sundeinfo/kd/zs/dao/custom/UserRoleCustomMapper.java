package com.sundeinfo.kd.zs.dao.custom;

import com.sundeinfo.kd.zs.dto.sys.UserRoleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleCustomMapper {

    List<UserRoleDTO> selectUserRole(@Param("userId") Integer userId );

}