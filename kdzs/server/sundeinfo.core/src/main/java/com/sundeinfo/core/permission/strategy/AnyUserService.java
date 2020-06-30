package com.sundeinfo.core.permission.strategy;

import com.sundeinfo.core.permission.domain.*;
import com.sundeinfo.core.permission.model.AnyUser;
import com.sundeinfo.core.permission.model.GeneralUserAuthority;
import com.sundeinfo.core.permission.service.*;
import com.sundeinfo.core.permission.utility.AuthorityCreater;
import com.sundeinfo.core.utility.ReflectUtils;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnyUserService extends AbstractService<AnyUserService> implements UserDetailsService {

    private UserDomainService userService;

    private AdminDomainService adminService;

    private OrganizationDomainService organizationService;

    private RoleDomainService roleService;

    private RolePermissionDomainService rolePermissionService;

    public void setServices(UserDomainService userService,
                            AdminDomainService adminService,
                            OrganizationDomainService organizationService,
                            RoleDomainService roleService,
                            RolePermissionDomainService rolePermissionService){
        this.userService = userService;
        this.adminService = adminService;
        this.organizationService = organizationService;
        this.roleService = roleService;
        this.rolePermissionService = rolePermissionService;
    }

    public UserDetails loadUserByUsername(String username){
        return getAnyUser(username);
    }

    public AnyUser getAnyUser(String username){
        try{
            UserDomain user = (UserDomain)userService.findByUsername(username);
            if(user == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            AdminDomain admin = (AdminDomain)adminService.findByUser(user.getId());
            List<OrganizationDomain> organizations = organizationService.findOrganizationByUserId(user.getId());
            List<RoleDomain> roles = roleService.findCompleteRolesByUserId(user.getId());
            List<RolePermissionDomain> rolePermissions = new ArrayList<>();
            List<GeneralUserAuthority> authorities = new ArrayList<>();

            if (roles.size() > 0){
                List<Integer> roleIds = ReflectUtils.reflectDataList(roles,"id", Integer.class);
                rolePermissions = rolePermissionService.findPermissionByRoleIds(roleIds);
            }
            if (admin != null) {
                rolePermissions = rolePermissionService.findPermissionAll();
            }
            for (RolePermissionDomain rolePermission:rolePermissions) {
                authorities.add(new GeneralUserAuthority(AuthorityCreater.getActionAuthority(rolePermission),rolePermission));
            }
            AnyUser anyUser = new AnyUser(user,authorities);
            anyUser.setAdmin(admin);
            anyUser.setOrganizations(organizations);
            anyUser.setRoles(roles);
            anyUser.setRolePermissions(rolePermissions);
            logger.debug("AnyUser:" + anyUser.toString());
            return anyUser;
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e){
            logger.error("反射对象属性时发生异常",e);
            throw new RuntimeException("反射对象属性时发生异常");
        }
    }


}
