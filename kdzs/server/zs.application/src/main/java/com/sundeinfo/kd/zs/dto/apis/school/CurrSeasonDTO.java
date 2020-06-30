package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

import java.util.Date;

@Data
public class CurrSeasonDTO {
    private Integer seasonId;
    private String recruitYear;
    private String onRecruitYear;
    private Integer recruitSeason;
    private Integer schoolId;
    private Date recruitStartDate;
    private Date recruitEndDate;
    public CurrSeasonDTO() {}

    public CurrSeasonDTO(Integer seasonId, String recruitYear, Integer recruitSeason,Date recruitStartDate,Date recruitEndDate) {
        this.seasonId = seasonId;
        this.recruitYear = recruitYear;
        this.recruitSeason = recruitSeason;
        this.recruitStartDate = recruitStartDate;
        this.recruitEndDate = recruitEndDate;
    }
}
