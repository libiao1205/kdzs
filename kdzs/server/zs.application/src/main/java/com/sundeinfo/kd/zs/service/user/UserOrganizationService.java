package com.sundeinfo.kd.zs.service.user;

import com.sundeinfo.core.permission.domain.OrganizationDomain;
import com.sundeinfo.core.permission.service.OrganizationDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.custom.SchoolTeacherCustomMapper;
import com.sundeinfo.kd.zs.dao.custom.model.SchoolTeacherCustom;
import com.sundeinfo.kd.zs.dao.model.SchoolTeacherExample;
import com.sundeinfo.kd.zs.dto.sys.UserOrganizationDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserOrganizationService extends AbstractService<UserOrganizationService> implements OrganizationDomainService<UserOrganizationService, OrganizationDomain> {

    @Resource
    private SchoolTeacherCustomMapper schoolTeacherCustomMapper;

    @Override
    public List<OrganizationDomain> findOrganizationByUserId(Integer id) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        // 查询 用户与教师关系表，判断是否为教师
        SchoolTeacherExample schoolTeacherExample = new SchoolTeacherExample();
        schoolTeacherExample.createCriteria().andUseridEqualTo(id);
        List<SchoolTeacherCustom> schoolTeacherList = schoolTeacherCustomMapper.selectSchoolTeacher(id);

        // 教师
        if (schoolTeacherList.size() > 0) {
            List<OrganizationDomain> ret = new ArrayList<>();

            schoolTeacherList.forEach(item->{
                UserOrganizationDTO userOrganizationDTO = new UserOrganizationDTO();
                userOrganizationDTO.setId(item.getSchoolId());
                userOrganizationDTO.setName(item.getSchoolName());
                userOrganizationDTO.setShortName(item.getSchoolShortName());
                ret.add(userOrganizationDTO);
            });

            return ret;
        }
        return null;
    }
}
