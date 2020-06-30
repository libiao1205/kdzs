package com.sundeinfo.kd.zs.dto.apis.school;

import com.sundeinfo.kd.zs.dao.model.SchoolRecruitPlanKey;
import lombok.Data;

import java.util.Date;
@Data
public class SchoolRecruitPlanDTO  {
    private Integer id;

    private Integer schoolid;

    private Integer seasonid;

    private Integer recruitcount;

    private Integer undergraduatepeople;

    private Integer technicalpeople;

    private Integer gkpeople;

    private Boolean recruitplan;

    public SchoolRecruitPlanDTO(Integer id, Integer schoolid, Integer seasonid, Integer recruitcount, Boolean recruitplan) {
        this.id = id;
        this.schoolid = schoolid;
        this.seasonid = seasonid;
        this.recruitcount = recruitcount;
        this.recruitplan = recruitplan;
    }

    public SchoolRecruitPlanDTO(Integer undergraduatepeople, Integer technicalpeople, Integer gkpeople) {
        this.undergraduatepeople = undergraduatepeople;
        this.technicalpeople = technicalpeople;
        this.gkpeople = gkpeople;
    }
}