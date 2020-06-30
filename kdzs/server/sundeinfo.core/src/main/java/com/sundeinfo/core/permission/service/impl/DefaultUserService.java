package com.sundeinfo.core.permission.service.impl;

import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.core.permission.service.UserDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;

public class DefaultUserService extends AbstractService<DefaultUserService> implements UserDomainService<DefaultUserService,UserDomain> {

    @Override
    public UserDomain findByUsername(String username) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return null;
    }

    @Override
    public UserDomain createNewUser() {
        return null;
    }
}


