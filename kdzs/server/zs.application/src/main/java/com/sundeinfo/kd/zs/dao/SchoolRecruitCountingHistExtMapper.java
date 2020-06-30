package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingHist;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitCountingHistDTO;

import java.util.List;

public interface SchoolRecruitCountingHistExtMapper {
    List<RecruitCountingHistDTO> selectHistory(SchoolRecruitCountingHist history);
}
