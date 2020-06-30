package com.sundeinfo.core.permission.service.impl;

import com.sundeinfo.core.permission.domain.RolePermissionDomain;
import com.sundeinfo.core.permission.service.RolePermissionDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class DefaultRolePermissionService extends AbstractService<DefaultRolePermissionService> implements RolePermissionDomainService<DefaultRolePermissionService,RolePermissionDomain> {

    @Override
    public List<RolePermissionDomain> findPermissionByRoleIds(List<Integer> ids) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return new ArrayList<>();
    }

    @Override
    public List<RolePermissionDomain> findPermissionAll() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return new ArrayList<>();
    }

}


