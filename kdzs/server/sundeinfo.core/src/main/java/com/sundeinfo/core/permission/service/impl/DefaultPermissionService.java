package com.sundeinfo.core.permission.service.impl;

import com.sundeinfo.core.permission.domain.PermissionDomain;
import com.sundeinfo.core.permission.service.PermissionDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class DefaultPermissionService extends AbstractService<DefaultPermissionService> implements PermissionDomainService<DefaultPermissionService,PermissionDomain> {
    @Override
    public List<PermissionDomain> findAll() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return new ArrayList<>();
    }
}


