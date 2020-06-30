package com.sundeinfo.core.permission.service;

import com.sundeinfo.foundation.mvc.service.Service;

import java.util.List;

public interface RolePermissionDomainService<T,D> extends Service<T> {

    List<D> findPermissionByRoleIds(List<Integer> ids) throws InstantiationException,IllegalAccessException,NoSuchFieldException;

    List<D> findPermissionAll() throws InstantiationException,IllegalAccessException,NoSuchFieldException;

}
