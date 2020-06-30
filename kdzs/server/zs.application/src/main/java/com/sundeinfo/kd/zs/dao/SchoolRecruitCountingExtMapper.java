package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCounting;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingKey;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitCountingSearchDTO;

import java.util.List;

public interface SchoolRecruitCountingExtMapper {

    List<SchoolRecruitCounting> queryByPrimaryKey(ReqRecruitCountingSearchDTO reqRecruitCountingSearchDTO);

}
