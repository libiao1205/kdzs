package com.sundeinfo.kd.zs.service.user;

import com.sundeinfo.core.permission.domain.AdminDomain;
import com.sundeinfo.core.permission.service.AdminDomainService;
import com.sundeinfo.core.permission.service.impl.DefaultAdminService;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.custom.UserRoleCustomMapper;
import com.sundeinfo.kd.zs.dto.sys.AdminUserDTO;
import com.sundeinfo.kd.zs.dto.sys.UserRoleDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAdminService extends AbstractService<DefaultAdminService> implements AdminDomainService<DefaultAdminService, AdminDomain> {

    @Resource
    private UserRoleCustomMapper userRoleCustomMapper;

    @Override
    public AdminDomain findByUser(Integer id) throws InstantiationException, IllegalAccessException, NoSuchFieldException {

        List<UserRoleDTO> userRoleDTOS = userRoleCustomMapper.selectUserRole(id);

        if (userRoleDTOS.stream().filter(item -> "ROLE:ADMIN".equals(item.getRoleCode())).count() > 0) {
            AdminUserDTO adminUserDTO = new AdminUserDTO();
            adminUserDTO.setUserId(id);
            return adminUserDTO;
        }

        return null;
    }
}
