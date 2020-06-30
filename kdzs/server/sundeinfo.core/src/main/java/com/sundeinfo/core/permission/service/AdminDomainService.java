package com.sundeinfo.core.permission.service;

import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.foundation.mvc.service.Service;

public interface AdminDomainService<T,D> extends Service<T> {

    D findByUser(Integer id) throws InstantiationException,IllegalAccessException,NoSuchFieldException;

}
