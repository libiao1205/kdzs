package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

@Data
public class OutRecruitInfoDTO {
    private String schoolId;
    private String recruitDate;
    private int recruitYear;
    private int recruitMonth;
    private String recruitWeek;
    private String recruitSeason;

    private int technicalPeople;
    private int undergraduatePeople;
    private int gkPeople;
}
