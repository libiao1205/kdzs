package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDetailDTO;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitStatisticsDTO;

import java.util.Date;
import java.util.List;

public interface SchoolRecruitSumExtMapper {

    RecruitSumStatisticsDTO findAllStatistics(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO);

    List<RecruitSumStatisticsDetailDTO> findAllStatisticsDetailSearchEnd(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO);

    List<RecruitSumStatisticsDetailDTO> findAllStatisticsDetail(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO);

    List<Integer> findAllStatisticsQuiJiCurrSeasonSumPeopleDetail(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO);

    RecruitSumStatisticsDTO findAllRecruitCount(Integer seasonId,Integer schoolId,String schoolIds,String currComparisonDate,String oldComparisonDate);

    Date findMaxDate(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO);

    Date findMinDate(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO);

}