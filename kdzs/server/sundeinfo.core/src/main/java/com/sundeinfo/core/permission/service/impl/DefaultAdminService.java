package com.sundeinfo.core.permission.service.impl;

import com.sundeinfo.core.permission.domain.AdminDomain;
import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.core.permission.service.AdminDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;

public class DefaultAdminService extends AbstractService<DefaultAdminService> implements AdminDomainService<DefaultAdminService,AdminDomain> {
    @Override
    public AdminDomain findByUser(Integer id) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return null;
    }

}
