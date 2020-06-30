package com.sundeinfo.core.permission.utility;

import com.sundeinfo.core.permission.domain.AdminDomain;
import com.sundeinfo.core.permission.domain.PermissionDomain;
import com.sundeinfo.core.permission.domain.RolePermissionDomain;

public class AuthorityCreater {

    public static String getAdminAuthority(AdminDomain admin)
    {
        if (admin != null){
            return "ROLE_ADMIN";
        }
        else{
            return "ROLE_USER";
        }

    }

    public static String getActionAuthority(PermissionDomain permission)
    {
        //权限标识
        return String.format("code:%s^action:%s^kind:%s^function:%s^value:%s",
                permission.getCode(),
                permission.getAction(),
                permission.getKind(),
                permission.getFunction(),
                permission.getValue());
    }

    public static String getActionAuthority(RolePermissionDomain rolePermission)
    {
        //用户权限标识
        return rolePermission.getPermissionCode();
    }
}
