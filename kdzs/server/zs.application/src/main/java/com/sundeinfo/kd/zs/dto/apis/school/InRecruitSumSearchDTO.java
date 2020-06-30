package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InRecruitSumSearchDTO {
    private Date recruitDateSt;
    private Date recruitDateEd;
    private Date recruitDate;
    private List<String> schoolIds;
    private int sumType;
}
