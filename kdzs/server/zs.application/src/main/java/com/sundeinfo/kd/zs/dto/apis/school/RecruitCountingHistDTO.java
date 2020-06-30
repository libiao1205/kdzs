package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

import java.util.Date;

@Data
public class RecruitCountingHistDTO {
    private Integer undergraduatePeople;
    private Integer technicalPeople;
    private Integer gkPeople;
    private String name;
    private Date updateDate;

    public RecruitCountingHistDTO(Integer undergraduatePeople, Integer technicalPeople, Integer gkPeople, String name, Date updateDate) {
        this.undergraduatePeople = undergraduatePeople;
        this.technicalPeople = technicalPeople;
        this.gkPeople = gkPeople;
        this.name = name;
        this.updateDate = updateDate;
    }
}
