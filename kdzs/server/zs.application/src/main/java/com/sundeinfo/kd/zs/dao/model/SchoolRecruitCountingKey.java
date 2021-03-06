package com.sundeinfo.kd.zs.dao.model;

import java.util.Date;

public class SchoolRecruitCountingKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.seasonId
     *
     * @mbg.generated
     */
    private Integer seasonid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.schoolId
     *
     * @mbg.generated
     */
    private Integer schoolid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.recruitDate
     *
     * @mbg.generated
     */
    private Date recruitdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting
     *
     * @mbg.generated
     */
    public SchoolRecruitCountingKey(Integer seasonid, Integer schoolid, Date recruitdate) {
        this.seasonid = seasonid;
        this.schoolid = schoolid;
        this.recruitdate = recruitdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting
     *
     * @mbg.generated
     */
    public SchoolRecruitCountingKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.seasonId
     *
     * @return the value of b_school_recruit_counting.seasonId
     *
     * @mbg.generated
     */
    public Integer getSeasonid() {
        return seasonid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.seasonId
     *
     * @param seasonid the value for b_school_recruit_counting.seasonId
     *
     * @mbg.generated
     */
    public void setSeasonid(Integer seasonid) {
        this.seasonid = seasonid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.schoolId
     *
     * @return the value of b_school_recruit_counting.schoolId
     *
     * @mbg.generated
     */
    public Integer getSchoolid() {
        return schoolid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.schoolId
     *
     * @param schoolid the value for b_school_recruit_counting.schoolId
     *
     * @mbg.generated
     */
    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.recruitDate
     *
     * @return the value of b_school_recruit_counting.recruitDate
     *
     * @mbg.generated
     */
    public Date getRecruitdate() {
        return recruitdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.recruitDate
     *
     * @param recruitdate the value for b_school_recruit_counting.recruitDate
     *
     * @mbg.generated
     */
    public void setRecruitdate(Date recruitdate) {
        this.recruitdate = recruitdate;
    }
}