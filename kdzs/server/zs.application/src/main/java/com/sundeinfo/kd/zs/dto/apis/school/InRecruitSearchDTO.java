package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

import java.util.Date;

@Data
public class InRecruitSearchDTO {
    private String schoolId;
    private int rangeType;
    private Date recruitDate;

}
