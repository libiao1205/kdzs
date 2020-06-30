package com.sundeinfo.core.permission.service;

import com.sundeinfo.foundation.mvc.service.Service;

public interface ResourceDomainService<T,D> extends Service<T> {
    D getResourceById(long id) throws InstantiationException,IllegalAccessException,NoSuchFieldException;
}