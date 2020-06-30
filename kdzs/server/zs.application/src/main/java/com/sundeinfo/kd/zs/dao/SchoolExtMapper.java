package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.School;
import com.sundeinfo.kd.zs.dao.model.SchoolExample;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface SchoolExtMapper {

    List<SchoolDTO> selectCurrUserSchool(Integer userId,Integer seasonId);

    List<String> selectSchoolAll();

    List<SchoolDTO> selectSchoolByNamePlan(Integer seasonId,String name);

    List<SchoolDTO> selectSchoolByNameRevisePeople(Integer seasonId,String name);

}