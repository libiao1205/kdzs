package com.sundeinfo.core.permission.strategy;

import com.sundeinfo.core.permission.model.AnyUser;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GeneralAccessDecisionManager extends AbstractService<GeneralAccessDecisionManager> implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if(null== configAttributes || configAttributes.size() <=0) {
            return;
        }
        String needRole;
        AnyUser anyUser = (AnyUser) authentication.getPrincipal();
        for (ConfigAttribute configAttribute : configAttributes) {
            needRole = configAttribute.getAttribute();
            for (GrantedAuthority ga : anyUser.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("当前地址无权访问!!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
