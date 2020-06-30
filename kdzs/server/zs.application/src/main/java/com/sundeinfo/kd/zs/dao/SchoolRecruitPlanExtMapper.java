package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.SchoolRecruitPlan;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitPlanKey;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolRecruitPlanDTO;

import java.util.List;

public interface SchoolRecruitPlanExtMapper {

    Integer updateByPrimaryKeySelectiveExt(SchoolRecruitPlan schoolRecruitPlan);

    SchoolRecruitPlanDTO findAllPlanRecruitPlan(Integer seasonId,Integer schoolId);
}
