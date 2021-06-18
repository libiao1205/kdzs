package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

@Data
public class RecruitSumStatisticsDetailDTO {
    private String name;
    private Integer sort;
    private Double recruitCount;
    private Integer undergraduatePeople;
    private Integer oldUndergraduatePeople;
    private Integer technicalPeople;
    private Integer oldTechnicalPeople;
    private Integer gkPeople;
    private Integer oldGkPeople;
    private Integer chunQiuSumPeople;
    private Integer chunJiSumPeople;
    private Integer oldChunJiSumPeople;
    private Double sumPeople;
    private Double oldSumPeople;
    private Double recruitRate;
    private Double planAccomplishRate;
    private Integer rateType;
    private String cellBgColor;
    private Integer recruitPlan;//是否招生
    private String lastRecruitDate;//最后招生日期-- 管理员查看各分校是否按时填写招生人数

    public RecruitSumStatisticsDetailDTO(String name,Integer sort, Double recruitCount, Integer undergraduatePeople, Integer oldUndergraduatePeople, Integer technicalPeople, Integer oldTechnicalPeople, Integer gkPeople, Integer oldGkPeople, Double sumPeople, Double oldSumPeople) {
        this.name = name;
        this.sort = sort;
        this.recruitCount = recruitCount;
        this.undergraduatePeople = undergraduatePeople;
        this.oldUndergraduatePeople = oldUndergraduatePeople;
        this.technicalPeople = technicalPeople;
        this.oldTechnicalPeople = oldTechnicalPeople;
        this.gkPeople = gkPeople;
        this.oldGkPeople = oldGkPeople;
        this.sumPeople = sumPeople;
        this.oldSumPeople = oldSumPeople;

    }

    public RecruitSumStatisticsDetailDTO(String name,Integer sort,Integer recruitPlan, Double recruitCount, Integer undergraduatePeople, Integer technicalPeople, Integer gkPeople, Double sumPeople,String lastRecruitDate) {
        this.name = name;
        this.sort = sort;
        this.recruitCount = recruitCount;
        this.undergraduatePeople = undergraduatePeople;
        this.technicalPeople = technicalPeople;
        this.gkPeople = gkPeople;
        this.sumPeople = sumPeople;
        this.recruitPlan = recruitPlan;
        this.lastRecruitDate = lastRecruitDate;
    }

    public RecruitSumStatisticsDetailDTO() {
    }
}
