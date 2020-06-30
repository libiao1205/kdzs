package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;

@Data
public class RecruitSumStatisticsDTO {
    private Double sumPeople;
    private Double undergraduatePeople;
    private Double planUndergraduatePeople;
    private Double undergraduateRate;
    private int undergraduateRateType;//0:上升，1:下降
    private Double technicalPeople;
    private Double planTechnicalPeople;
    private Double technicalRate;
    private int technicalRateType;
    private Double gkPeople;
    private Double planGkPeople;
    private Double gkRate;
    private int gkRateType;
    private Integer recruitSeason;
    private Double recruitRate;
    private int rateType;//0:上升，1:下降

    public RecruitSumStatisticsDTO() {
    }

    public RecruitSumStatisticsDTO(Double sumPeople, Double undergraduatePeople, Double technicalPeople, Double gkPeople, Integer recruitSeason) {
        this.sumPeople = sumPeople;
        this.undergraduatePeople = undergraduatePeople;
        this.technicalPeople = technicalPeople;
        this.gkPeople = gkPeople;
        this.recruitSeason = recruitSeason;
    }
    public RecruitSumStatisticsDTO(Double undergraduatePeople, Double technicalPeople, Double gkPeople) {
        this.undergraduatePeople = undergraduatePeople;
        this.technicalPeople = technicalPeople;
        this.gkPeople = gkPeople;
    }

    public RecruitSumStatisticsDTO(Double sumPeople, Double recruitRate, int rateType, Double undergraduatePeople, Double undergraduateRate, int undergraduateRateType, Double technicalPeople, Double technicalRate, int technicalRateType, Double gkPeople, Double gkRate, int gkRateType) {
        this.sumPeople = sumPeople;
        this.undergraduatePeople = undergraduatePeople;
        this.undergraduateRate = undergraduateRate;
        this.undergraduateRateType = undergraduateRateType;
        this.technicalPeople = technicalPeople;
        this.technicalRate = technicalRate;
        this.technicalRateType = technicalRateType;
        this.gkPeople = gkPeople;
        this.gkRate = gkRate;
        this.gkRateType = gkRateType;
        this.recruitRate = recruitRate;
        this.rateType = rateType;
    }
}
