package com.sundeinfo.core.permission.service.impl;

import com.sundeinfo.core.permission.domain.RoleDomain;
import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.core.permission.service.RoleDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class DefaultRoleService extends AbstractService<DefaultRoleService> implements RoleDomainService<DefaultRoleService,RoleDomain> {

    @Override
    public List<RoleDomain> findCompleteRolesByUserId(Integer id) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return new ArrayList<>();
    }

}


