package com.sundeinfo.core.permission.service.impl;

import com.sundeinfo.core.permission.domain.OrganizationDomain;
import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.core.permission.service.OrganizationDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrganizationService extends AbstractService<DefaultOrganizationService> implements OrganizationDomainService<DefaultOrganizationService,OrganizationDomain> {
    @Override
    public List<OrganizationDomain> findOrganizationByUserId(Integer id) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        return new ArrayList<>();
    }
}
