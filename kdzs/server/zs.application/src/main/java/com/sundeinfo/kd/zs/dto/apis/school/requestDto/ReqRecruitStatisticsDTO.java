package com.sundeinfo.kd.zs.dto.apis.school.requestDto;

import lombok.Data;

import java.util.Date;

@Data
public class ReqRecruitStatisticsDTO {
    private Integer seasonId;
    private Integer schoolId;
    private String onRecruitYear;
    private String recruitYear;
    private Integer recruitSeason;
    private Integer sort;
    public ReqRecruitStatisticsDTO(){}
    public ReqRecruitStatisticsDTO(Integer schoolId, String onRecruitYear, String recruitYear, Integer recruitSeason) {
        this.schoolId = schoolId;
        this.onRecruitYear = onRecruitYear;
        this.recruitYear = recruitYear;
        this.recruitSeason = recruitSeason;
    }
}
