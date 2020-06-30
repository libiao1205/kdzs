package com.sundeinfo.core.permission.model;

import com.sundeinfo.core.permission.domain.RolePermissionDomain;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

@Data
public class GeneralUserAuthority implements GrantedAuthority {

    private String authority;

    private RolePermissionDomain rolePermission;

    public GeneralUserAuthority(String authority) {
        Assert.hasText(authority, "---------------------UserDomain Authority is null----------------------");
        this.authority = authority;
    }

    public GeneralUserAuthority(String authority, RolePermissionDomain rolePermission) {
        this(authority);
        Assert.notNull(rolePermission,"---------------------UserDomain RolePermissionDomain is null----------------------");
        this.rolePermission = rolePermission;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
