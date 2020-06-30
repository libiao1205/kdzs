package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

import java.util.Date;

@Data
public class RecruitSeasonDTO {

    private Integer id;//季节id
    private Integer schoolId;
    private Integer seasonId;//季节id
    private String recruitYear;
    private Integer recruitSeason;
    private Date recruitStartDate;
    private String startDate;
    private String endDate;
    private Date recruitEndDate;
    private Integer status;
    private Integer isDelete;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;
    public RecruitSeasonDTO() {};
    public RecruitSeasonDTO(Integer id, String recruitYear, Integer recruitSeason, Date recruitStartDate, Date recruitEndDate, Integer status, Integer isDelete, Date createTime, Integer createBy, Date updateTime, Integer updateBy) {
        this.id = id;
        this.recruitYear = recruitYear;
        this.recruitSeason = recruitSeason;
        this.recruitStartDate = recruitStartDate;
        this.recruitEndDate = recruitEndDate;
        this.status = status;
        this.isDelete = isDelete;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }
}
