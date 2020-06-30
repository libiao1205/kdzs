package com.sundeinfo.kd.zs.service.user;

import com.sundeinfo.core.permission.domain.RoleDomain;
import com.sundeinfo.core.permission.service.RoleDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.RoleMapper;
import com.sundeinfo.kd.zs.dao.UserRoleMapper;
import com.sundeinfo.kd.zs.dao.custom.UserRoleCustomMapper;
import com.sundeinfo.kd.zs.dao.model.UserRoleExample;
import com.sundeinfo.kd.zs.dao.model.UserRoleKey;
import com.sundeinfo.kd.zs.dto.sys.UserRoleDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService extends AbstractService<UserRoleService> implements RoleDomainService<UserRoleService, RoleDomain> {
    @Resource
    private UserRoleCustomMapper userRoleCustomMapper;

    @Override
    public List<RoleDomain> findCompleteRolesByUserId(Integer id) throws InstantiationException, IllegalAccessException, NoSuchFieldException {

        List<UserRoleDTO> userRoles = userRoleCustomMapper.selectUserRole(id);
        if (userRoles.size() == 0) {
            return new ArrayList<>();
        }
        return new ArrayList<>(userRoles);
    }
}
