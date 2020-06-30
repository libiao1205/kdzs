package com.sundeinfo.core.permission.service;

import com.sundeinfo.foundation.mvc.service.Service;

public interface UserDomainService<T,D> extends Service<T> {

    D findByUsername(String username)  throws InstantiationException,IllegalAccessException,NoSuchFieldException;

    D createNewUser();
}
