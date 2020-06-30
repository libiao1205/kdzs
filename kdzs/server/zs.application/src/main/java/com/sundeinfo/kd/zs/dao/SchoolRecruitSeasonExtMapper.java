package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeason;
import com.sundeinfo.kd.zs.dto.apis.school.CurrSeasonDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSeasonDTO;

import java.util.Date;
import java.util.List;

public interface SchoolRecruitSeasonExtMapper {

    CurrSeasonDTO selectByCurrSeason();

    List<Integer> selectByStatus();

    int insertSelective(RecruitSeasonDTO record);

    Integer selectByOnRecruitYear(String recruitYear,Integer recruitSeason);

    int  updateByPrimaryKey(RecruitSeasonDTO recruitSeason);

    int updateByStatus(String id);

    Long seasonIsExist(RecruitSeasonDTO schoolRecruitSeason);
}