package com.sundeinfo.kd.zs.dto.apis.school.requestDto;

import lombok.Data;

import java.util.Date;

@Data
public class ReqRecruitCountingSearchDTO {
    private Integer schoolId;
    private Integer seasonId;
    private Integer type;//type 1：前三天，2：前一周，3：前一个月，4：前一季度
}
