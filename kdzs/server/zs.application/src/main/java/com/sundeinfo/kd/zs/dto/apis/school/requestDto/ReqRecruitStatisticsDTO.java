package com.sundeinfo.kd.zs.dto.apis.school.requestDto;

import lombok.Data;

import java.util.Date;

@Data
public class ReqRecruitStatisticsDTO {
    private Integer seasonId;
    private Integer schoolId;
    private String schoolIds;
    private String onRecruitYear;
    private String recruitYear;
    private Integer recruitSeason;
    private Integer sort;
    private String currComparisonDate;
    private String oldComparisonDate;
    private String currDate;//当春季招生提前开始例如：20年底开始21年的春季招生，进行同期比时需要用到此参数计算时间
    public ReqRecruitStatisticsDTO(){}
    public ReqRecruitStatisticsDTO(Integer schoolId, String onRecruitYear, String recruitYear, Integer recruitSeason) {
        this.schoolId = schoolId;
        this.onRecruitYear = onRecruitYear;
        this.recruitYear = recruitYear;
        this.recruitSeason = recruitSeason;
    }
}
