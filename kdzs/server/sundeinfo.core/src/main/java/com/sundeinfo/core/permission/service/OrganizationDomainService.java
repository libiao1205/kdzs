package com.sundeinfo.core.permission.service;

import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.foundation.mvc.service.Service;

import java.util.List;

public interface OrganizationDomainService<T,D> extends Service<T> {

    List<D> findOrganizationByUserId(Integer id) throws IllegalAccessException, InstantiationException,NoSuchFieldException;

}
