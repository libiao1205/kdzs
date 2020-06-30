package com.sundeinfo.core.permission.strategy;


import com.sundeinfo.core.permission.service.*;
import com.sundeinfo.core.permission.service.impl.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

public class SecurityServiceConfigurationSupport implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private AnyUserService anyUserService;

    private GeneralMetadataSourceService generalMetadataSourceService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setServices(){
        anyUserService = (AnyUserService)applicationContext.getBean("anyUserService");
        anyUserService.setServices(new DefaultUserService(),
                new DefaultAdminService(),
                new DefaultOrganizationService(),
                new DefaultRoleService(),
                new DefaultRolePermissionService());
        generalMetadataSourceService = (GeneralMetadataSourceService)applicationContext.getBean("generalMetadataSourceService");
        generalMetadataSourceService.setServices(new DefaultPermissionService());
    }

    public void setServices(UserDomainService userService,
                            AdminDomainService adminService,
                            OrganizationDomainService organizationService,
                            RoleDomainService roleService,
                            RolePermissionDomainService rolePermissionService,
                            PermissionDomainService permissionDomainService){
        anyUserService = (AnyUserService)applicationContext.getBean("anyUserService");
        anyUserService.setServices(Optional.ofNullable(userService).orElse(new DefaultUserService()),
                                    Optional.ofNullable(adminService).orElse(new DefaultAdminService()),
                                    Optional.ofNullable(organizationService).orElse(new DefaultOrganizationService()),
                                    Optional.ofNullable(roleService).orElse(new DefaultRoleService()),
                                    Optional.ofNullable(rolePermissionService).orElse(new DefaultRolePermissionService()));
        generalMetadataSourceService = (GeneralMetadataSourceService)applicationContext.getBean("generalMetadataSourceService");
        generalMetadataSourceService.setServices(Optional.ofNullable(permissionDomainService).orElse(new DefaultPermissionService()));
    }

}
