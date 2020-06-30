package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

@Data
public class SchoolDTO {
    private Integer schoolId;

    private String name;

    private String shortname;

    private Integer areaid;

    private Integer planId;

    private Integer recruitPlan;

    private Integer recruitCount;

    private Integer undergraduatePeople;

    private Integer technicalPeople;

    private Integer gkPeople;

    private Integer revise;

    public SchoolDTO(Integer schoolId, String name, String shortname, Integer areaid,Integer recruitPlan,Integer recruitCount) {
        this.schoolId = schoolId;
        this.name = name;
        this.shortname = shortname;
        this.areaid = areaid;
        this.recruitPlan = recruitPlan;
        this.recruitCount = recruitCount;
    }
    public SchoolDTO(Integer schoolId, String name, Integer planId, Integer recruitPlan, Integer recruitCount,Integer gkPeople,Integer technicalPeople,Integer undergraduatePeople) {
        this.schoolId = schoolId;
        this.name = name;
        this.planId = planId;
        this.recruitPlan = recruitPlan;
        this.recruitCount = recruitCount;
        this.gkPeople = gkPeople;
        this.technicalPeople = technicalPeople;
        this.undergraduatePeople = undergraduatePeople;
    }

    public SchoolDTO( String name,Integer schoolId, Integer undergraduatePeople, Integer technicalPeople, Integer gkPeople,Integer revise) {
        this.schoolId = schoolId;
        this.name = name;
        this.undergraduatePeople = undergraduatePeople;
        this.technicalPeople = technicalPeople;
        this.gkPeople = gkPeople;
        this.revise = revise;
    }
}