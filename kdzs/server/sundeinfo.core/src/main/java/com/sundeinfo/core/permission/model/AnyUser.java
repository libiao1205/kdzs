package com.sundeinfo.core.permission.model;

import com.sundeinfo.core.permission.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnyUser extends User implements Serializable {

    private Integer id;

    private String name;

    private UserDomain user;

    private AdminDomain admin;

    private List<OrganizationDomain> organizations;

    private List<RoleDomain> roles;

    private List<RolePermissionDomain> rolePermissions;

    private int unitId;

    public AnyUser(UserDomain user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.id = user.getId();
        this.user = user;
        this.name = user.getName();
    }

    @Override
    public String toString() {
        return "AnyUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", admin=" + admin +
                ", organizations=" + organizations +
                ", roles=" + roles +
                ", rolePermissions=" + rolePermissions +
                ", unitId=" + unitId +
                '}';
    }
}
