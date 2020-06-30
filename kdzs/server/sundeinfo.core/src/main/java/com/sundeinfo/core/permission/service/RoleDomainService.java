package com.sundeinfo.core.permission.service;

import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.foundation.mvc.service.Service;

import java.util.List;

public interface RoleDomainService<T,D> extends Service<T> {

    List<D> findCompleteRolesByUserId(Integer id) throws InstantiationException,IllegalAccessException,NoSuchFieldException;

}
