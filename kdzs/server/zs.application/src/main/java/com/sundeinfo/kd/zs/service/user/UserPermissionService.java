package com.sundeinfo.kd.zs.service.user;

import com.sundeinfo.core.permission.domain.RolePermissionDomain;
import com.sundeinfo.core.permission.service.RolePermissionDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.PermissionMapper;
import com.sundeinfo.kd.zs.dao.custom.RolePermissionCustomMapper;
import com.sundeinfo.kd.zs.dao.model.Permission;
import com.sundeinfo.kd.zs.dao.model.PermissionExample;
import com.sundeinfo.kd.zs.dto.sys.UserPermissionDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPermissionService extends AbstractService<UserPermissionService> implements RolePermissionDomainService<UserPermissionService, RolePermissionDomain> {
    @Resource
    private RolePermissionCustomMapper rolePermissionCustomMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<RolePermissionDomain> findPermissionByRoleIds(List<Integer> ids) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<UserPermissionDTO> userPermissionDTOS = rolePermissionCustomMapper.selectRolePermission(ids);
        if (userPermissionDTOS.size() ==0) {
            return new ArrayList<>();
        }
        return new ArrayList<>(userPermissionDTOS);
    }

    @Override
    public List<RolePermissionDomain> findPermissionAll() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());

        if (permissions.size() ==0) {
            return new ArrayList<>();
        }

        List<RolePermissionDomain> rolePermissionDomainList = new ArrayList<>();
        permissions.forEach(item -> {
            UserPermissionDTO userPermissionDTO = new UserPermissionDTO();
            userPermissionDTO.setId(item.getId());
            userPermissionDTO.setPermissionCode(item.getPermissioncode());
            userPermissionDTO.setDescription(item.getDescription());
            rolePermissionDomainList.add(userPermissionDTO);
        });
        return rolePermissionDomainList;
    }
}
