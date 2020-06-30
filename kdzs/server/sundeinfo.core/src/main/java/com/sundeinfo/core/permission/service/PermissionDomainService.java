package com.sundeinfo.core.permission.service;

import com.sundeinfo.foundation.mvc.service.Service;

import java.util.List;

public interface PermissionDomainService<T,D> extends Service<T> {

    List<D> findAll() throws InstantiationException,IllegalAccessException,NoSuchFieldException;
}
