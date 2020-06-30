package com.sundeinfo.core.permission;

import com.fasterxml.jackson.core.util.InternCache;
import com.sundeinfo.core.permission.domain.PermissionDomain;
import com.sundeinfo.core.permission.domain.RolePermissionDomain;
import com.sundeinfo.core.permission.model.AnyResource;
import com.sundeinfo.core.permission.model.GeneralConfigAttribute;
import com.sundeinfo.core.permission.model.GeneralUserAuthority;
import com.sundeinfo.core.permission.service.ResourceDomainService;
import com.sundeinfo.core.permission.strategy.GeneralMetadataSourceService;
import com.sundeinfo.core.permission.utility.AuthenticationGetter;
import com.sundeinfo.foundation.domain.Entity;
import com.sundeinfo.foundation.mvc.component.AbstractComponent;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.result.ResultFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstractPermissionChecker<T> extends AbstractComponent<T> {

    @Autowired
    private AuthenticationGetter authenticationGetter;

    @Autowired
    private GeneralMetadataSourceService generalMetadataSourceService;

    @Autowired
    private ResourceDomainService resourceService;

    public Object invoke(ProceedingJoinPoint point, Permissionable permissionable) throws Throwable {
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Collection<GrantedAuthority> userAuthorities = authenticationGetter.getAnyUser().getAuthorities();
            Collection<ConfigAttribute> configAttributes = generalMetadataSourceService.getAttributes(request);

            if(null== configAttributes || configAttributes.size() <=0) {
                return point.proceed();
            }

            String bestMatch = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
            Result result = tryGetResource(point,permissionable);

            GeneralConfigAttribute generalConfigAttribute = getConfigAttribute(configAttributes,permissionable.function().value());
            PermissionDomain permission = generalConfigAttribute.getPermission();

            switch (permission.getCode()){
                case "folder":
//                    GeneralUserAuthority generalUserAuthority = getGrantedAuthority(userAuthorities,permission.getCode(),result);
//                    RolePermissionDomain rolePermission = generalUserAuthority.getRolePermission();
//                    boolean hasPermission = request.getMethod().toLowerCase().equals(permission.getKind());
//                    hasPermission = hasPermission && ((rolePermission.getValue() & permission.getValue()) == 1);
//                    if (hasPermission)return point.proceed();
                case "file":
                    break;
                case "link":
                    break;
                case "message":
                    break;
            }
            logger.error(String.format("-------------------没有处理的权限类型:%s-------------------",permission.getCode()));
            return ResultFactory.getDataMessage(RequestState.REQUEST_ILLEGAL);
        }catch (Exception e){
            return ResultFactory.getDataMessage(RequestState.REQUEST_ILLEGAL);
        }
    }

    private Result tryGetResource(ProceedingJoinPoint point, Permissionable permissionable){
        Result result = new Result();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map pathVariables = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables.containsKey(permissionable.dataKey())){
            result.id = Integer.parseInt(pathVariables.get(permissionable.dataKey()).toString());
            result.hasId = true;
            return result;
        }else{
            Object[] args = point.getArgs();
            if (args != null && args.length > 0 && args[0] instanceof Entity) {
                result.id = ((Entity)args[0]).getId();
                result.hasId = true;
                return result;
            }
        }
        result.id = 0;
        result.hasId = false;
        return result;
    }

    private GeneralConfigAttribute getConfigAttribute(Collection<ConfigAttribute> configAttributes, String function){
        List<ConfigAttribute> selected = configAttributes.stream().filter(configAttribute -> function.equals(((GeneralConfigAttribute)configAttribute).getPermission().getFunction())).collect(Collectors.toList());
        if (selected.size() != 1) {
            logger.error(String.format("-------------------权限设置异常:%s配置了多个-------------------",function));
        }
        return (GeneralConfigAttribute)selected.get(0);
    }

//    private GeneralUserAuthority getGrantedAuthority(Collection<GrantedAuthority> userAuthorities, String code, Result result){
//        if (!result.hasId){
//            logger.error(String.format("-------------------请求没有找到id:%s-------------------",code));
//            return null;
//        }
//        List<GrantedAuthority> notNullSelected = userAuthorities.stream().filter(authority -> ((GeneralUserAuthority)authority).getRolePermission() != null).collect(Collectors.toList());
//        List<GrantedAuthority> sameCodeSelected = notNullSelected.stream().filter(authority -> code.equals(((GeneralUserAuthority)authority).getRolePermission().getCode())).collect(Collectors.toList());
//        List<GrantedAuthority> sameResIdSelected = sameCodeSelected.stream().filter(authority -> result.id == ((GeneralUserAuthority) authority).getRolePermission().getResourceId()).collect(Collectors.toList());
//
//
//        if (sameResIdSelected.size() != 1) {
//            logger.error(String.format("-------------------角色权限设置异常:%s配置了多个-------------------",code));
//        }
//        return (GeneralUserAuthority)sameResIdSelected.get(0);
//    }


    private boolean isParental(String resourceKind,long resourceId,long parentId) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        if (resourceId == 0) return true;
        if (parentId == 0) return true;
        if (resourceId == parentId) return true;
        AnyResource resource =  (AnyResource)resourceService.<AnyResource>getResourceById(resourceId);
        return resource.getFullPath().trim().contains(parentId + "");
    }

    private class Result{
        public boolean hasId;
        public Integer id;
    }
}