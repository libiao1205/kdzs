package com.sundeinfo.kd.zs.configuration;

import com.sundeinfo.core.interceptor.AdditionalJacksonAnnotationIntrospector;
import com.sundeinfo.core.log.LogConfigurationSupport;
import com.sundeinfo.core.permission.strategy.SecurityServiceConfigurationSupport;
import com.sundeinfo.kd.zs.dto.sys.AdminUserDTO;
import com.sundeinfo.kd.zs.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

@Configuration
public class BeanRegistrar {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserPermissionService rolePermissionService;

    @Autowired
    private UserAdminService userAdminService;

    @Autowired
    private UserOrganizationService userOrganizationService;

    @Bean
    public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBeanOP() {
        Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
        jackson2ObjectMapperFactoryBean.setAnnotationIntrospector(new AdditionalJacksonAnnotationIntrospector());
        return jackson2ObjectMapperFactoryBean;
    }

    @Bean
    public LogConfigurationSupport logConfigurationSupport() {
        LogConfigurationSupport logConfigurationSupport = new LogConfigurationSupport();
        logConfigurationSupport.setApplicationContext(applicationContext);
        return logConfigurationSupport;
    }

    @Bean
    public SecurityServiceConfigurationSupport securityServiceConfigurationSupport() {
        SecurityServiceConfigurationSupport securityServiceConfigurationSupport = new SecurityServiceConfigurationSupport();
        securityServiceConfigurationSupport.setApplicationContext(applicationContext);
        securityServiceConfigurationSupport.setServices(userService, userAdminService, userOrganizationService, userRoleService, rolePermissionService, null);
        return securityServiceConfigurationSupport;
    }
}