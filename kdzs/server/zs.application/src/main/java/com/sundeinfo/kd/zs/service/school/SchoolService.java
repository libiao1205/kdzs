package com.sundeinfo.kd.zs.service.school;

import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolExtMapper;
import com.sundeinfo.kd.zs.dao.SchoolMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitSeasonExtMapper;
import com.sundeinfo.kd.zs.dao.model.School;
import com.sundeinfo.kd.zs.dao.model.SchoolExample;
import com.sundeinfo.kd.zs.dto.apis.school.CurrSeasonDTO;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolDTO;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolService extends AbstractService<SchoolService> {

    @Resource
    SchoolExtMapper schoolExtMapper;

    @Resource
    SchoolRecruitSeasonExtMapper schoolRecruitSeasonExtMapper;

    @Autowired
    SeasonService seasonService;

    public List<SchoolDTO> findSchoolAll(){
        SchoolExample example = new SchoolExample();
        example.setOrderByClause("address asc");
        CurrSeasonDTO currSeasonDTO = seasonService.getCurrSeason();
        List<SchoolDTO> list = schoolExtMapper.selectSchoolAll(currSeasonDTO.getSeasonId());
        SchoolDTO school = new SchoolDTO();
        school.setName("全系统");
        list.add(0,school);
        return list;
    }

    public List<SchoolDTO> findSchoolByNamePlan(Integer seasonId,String name) {
        return schoolExtMapper.selectSchoolByNamePlan(seasonId,name);
    }

    public List<SchoolDTO> findSchoolByNameRevisePeople(Integer seasonId,String name)  {
        return schoolExtMapper.selectSchoolByNameRevisePeople(seasonId,name);
    }

    public SchoolDTO findCurrUserSchool() {
        Integer userId = UserHelper.getCurrentUserId();
        CurrSeasonDTO currSeasonDTO = schoolRecruitSeasonExtMapper.selectByCurrSeason();
        Integer seasonId = null;
        if(currSeasonDTO != null){
            seasonId = currSeasonDTO.getSeasonId();
        }
        List<SchoolDTO> schoolDTOList = schoolExtMapper.selectCurrUserSchool(userId,seasonId);
        if(schoolDTOList.size()>0){
            return schoolDTOList.get(0);
        }else{
            return null;
        }
    }

}
