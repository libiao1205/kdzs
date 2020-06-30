package com.sundeinfo.core.permission.service.impl;

import com.sundeinfo.core.permission.model.AnyResource;
import com.sundeinfo.core.permission.service.ResourceDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;

public class DefaultResourceService extends AbstractService<DefaultResourceService> implements ResourceDomainService<DefaultResourceService,AnyResource> {

    @Override
    public AnyResource getResourceById(long id) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        return null;
    }
}


