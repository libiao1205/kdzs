package com.sundeinfo.kd.zs.dao.model;

import java.util.Date;

public class SchoolRecruitCounting extends SchoolRecruitCountingKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.undergraduatePeople
     *
     * @mbg.generated
     */
    private Integer undergraduatepeople;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.technicalPeople
     *
     * @mbg.generated
     */
    private Integer technicalpeople;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.gkPeople
     *
     * @mbg.generated
     */
    private Integer gkpeople;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.isdelete
     *
     * @mbg.generated
     */
    private Boolean isdelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.createtime
     *
     * @mbg.generated
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.createby
     *
     * @mbg.generated
     */
    private Integer createby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.updatetime
     *
     * @mbg.generated
     */
    private Date updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_school_recruit_counting.updateby
     *
     * @mbg.generated
     */
    private Integer updateby;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting
     *
     * @mbg.generated
     */
    public SchoolRecruitCounting(Integer seasonid, Integer schoolid, Date recruitdate, Integer undergraduatepeople, Integer technicalpeople, Integer gkpeople, Boolean isdelete, Date createtime, Integer createby, Date updatetime, Integer updateby) {
        super(seasonid, schoolid, recruitdate);
        this.undergraduatepeople = undergraduatepeople;
        this.technicalpeople = technicalpeople;
        this.gkpeople = gkpeople;
        this.isdelete = isdelete;
        this.createtime = createtime;
        this.createby = createby;
        this.updatetime = updatetime;
        this.updateby = updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting
     *
     * @mbg.generated
     */
    public SchoolRecruitCounting() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.undergraduatePeople
     *
     * @return the value of b_school_recruit_counting.undergraduatePeople
     *
     * @mbg.generated
     */
    public Integer getUndergraduatepeople() {
        return undergraduatepeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.undergraduatePeople
     *
     * @param undergraduatepeople the value for b_school_recruit_counting.undergraduatePeople
     *
     * @mbg.generated
     */
    public void setUndergraduatepeople(Integer undergraduatepeople) {
        this.undergraduatepeople = undergraduatepeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.technicalPeople
     *
     * @return the value of b_school_recruit_counting.technicalPeople
     *
     * @mbg.generated
     */
    public Integer getTechnicalpeople() {
        return technicalpeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.technicalPeople
     *
     * @param technicalpeople the value for b_school_recruit_counting.technicalPeople
     *
     * @mbg.generated
     */
    public void setTechnicalpeople(Integer technicalpeople) {
        this.technicalpeople = technicalpeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.gkPeople
     *
     * @return the value of b_school_recruit_counting.gkPeople
     *
     * @mbg.generated
     */
    public Integer getGkpeople() {
        return gkpeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.gkPeople
     *
     * @param gkpeople the value for b_school_recruit_counting.gkPeople
     *
     * @mbg.generated
     */
    public void setGkpeople(Integer gkpeople) {
        this.gkpeople = gkpeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.isdelete
     *
     * @return the value of b_school_recruit_counting.isdelete
     *
     * @mbg.generated
     */
    public Boolean getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.isdelete
     *
     * @param isdelete the value for b_school_recruit_counting.isdelete
     *
     * @mbg.generated
     */
    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.createtime
     *
     * @return the value of b_school_recruit_counting.createtime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.createtime
     *
     * @param createtime the value for b_school_recruit_counting.createtime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.createby
     *
     * @return the value of b_school_recruit_counting.createby
     *
     * @mbg.generated
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.createby
     *
     * @param createby the value for b_school_recruit_counting.createby
     *
     * @mbg.generated
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.updatetime
     *
     * @return the value of b_school_recruit_counting.updatetime
     *
     * @mbg.generated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.updatetime
     *
     * @param updatetime the value for b_school_recruit_counting.updatetime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_school_recruit_counting.updateby
     *
     * @return the value of b_school_recruit_counting.updateby
     *
     * @mbg.generated
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_school_recruit_counting.updateby
     *
     * @param updateby the value for b_school_recruit_counting.updateby
     *
     * @mbg.generated
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }
}