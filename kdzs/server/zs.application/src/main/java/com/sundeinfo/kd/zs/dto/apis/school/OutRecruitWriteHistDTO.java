package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

@Data
public class OutRecruitWriteHistDTO {

    private String userName;
    private String recruitDate;
    private String writeDate;
    private int technicalPeople;
    private int undergraduatePeople;
    private int gkPeople;

}
