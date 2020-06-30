package com.sundeinfo.kd.zs.utility;

import com.sundeinfo.core.permission.domain.RolePermissionDomain;
import com.sundeinfo.core.permission.model.AnyUser;
import com.sundeinfo.core.permission.strategy.token.AuthenticationResponse;
import com.sundeinfo.core.permission.utility.AuthorityCreater;
import com.sundeinfo.foundation.domain.Entity;
import com.sundeinfo.kd.zs.dto.sys.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class UserHelper {

    public static Integer getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDTO)((AnyUser)authentication.getPrincipal()).getUser()).getId();
    }

    public static List<Integer> getCurrentUserOrgs(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((AnyUser)authentication.getPrincipal()).getOrganizations().stream().map(Entity::getId).collect(Collectors.toList());
    }


    public static List<String> getCurrentUserPermissions(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((AnyUser)authentication.getPrincipal()).getRolePermissions().stream().map(RolePermissionDomain::getPermissionCode).collect(Collectors.toList());
    }

    public static AuthenticationResponse createJwtAuthenticationResponse(String token){
        Assert.hasText(token,"Token为空!");
        AnyUser anyUser = (AnyUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthenticationResponse response = new AuthenticationResponse();
        response.setName(anyUser.getUser().getName());
        response.setToken(token);
        response.setOrganizations(anyUser.getOrganizations());
        response.setOperation(AuthorityCreater.getAdminAuthority(anyUser.getAdmin()));
        response.setRoleDomains(anyUser.getRoles());
        return response;
    }
}
