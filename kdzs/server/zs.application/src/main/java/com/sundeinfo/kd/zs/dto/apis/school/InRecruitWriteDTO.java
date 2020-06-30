package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

import java.util.Date;

@Data
public class InRecruitWriteDTO {
    private String schoolId;
    private String recruitDate;
    private int technicalPeople;
    private int undergraduatePeople;
    private int gkPeople;
}
