package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

@Data
public class OutRecruitSumDay {

    private String sumDate;

    private int technicalPeople;
    private int undergraduatePeople;
    private int gkPeople;

    private int technicalPeopleYesterday;
    private int undergraduatePeopleYesterday;
    private int gkPeopleYesterday;

}
