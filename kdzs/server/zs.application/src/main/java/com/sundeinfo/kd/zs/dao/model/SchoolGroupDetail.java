package com.sundeinfo.kd.zs.dao.model;

import java.util.Date;

public class SchoolGroupDetail extends SchoolGroupDetailKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group_detail.isdelete
     *
     * @mbg.generated
     */
    private Boolean isdelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group_detail.createtime
     *
     * @mbg.generated
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group_detail.createby
     *
     * @mbg.generated
     */
    private Integer createby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group_detail.updatetime
     *
     * @mbg.generated
     */
    private Date updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group_detail.updateby
     *
     * @mbg.generated
     */
    private Integer updateby;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school_group_detail
     *
     * @mbg.generated
     */
    public SchoolGroupDetail(Integer groupid, Integer schoolid, Boolean isdelete, Date createtime, Integer createby, Date updatetime, Integer updateby) {
        super(groupid, schoolid);
        this.isdelete = isdelete;
        this.createtime = createtime;
        this.createby = createby;
        this.updatetime = updatetime;
        this.updateby = updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school_group_detail
     *
     * @mbg.generated
     */
    public SchoolGroupDetail() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group_detail.isdelete
     *
     * @return the value of m_school_group_detail.isdelete
     *
     * @mbg.generated
     */
    public Boolean getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group_detail.isdelete
     *
     * @param isdelete the value for m_school_group_detail.isdelete
     *
     * @mbg.generated
     */
    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group_detail.createtime
     *
     * @return the value of m_school_group_detail.createtime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group_detail.createtime
     *
     * @param createtime the value for m_school_group_detail.createtime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group_detail.createby
     *
     * @return the value of m_school_group_detail.createby
     *
     * @mbg.generated
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group_detail.createby
     *
     * @param createby the value for m_school_group_detail.createby
     *
     * @mbg.generated
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group_detail.updatetime
     *
     * @return the value of m_school_group_detail.updatetime
     *
     * @mbg.generated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group_detail.updatetime
     *
     * @param updatetime the value for m_school_group_detail.updatetime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group_detail.updateby
     *
     * @return the value of m_school_group_detail.updateby
     *
     * @mbg.generated
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group_detail.updateby
     *
     * @param updateby the value for m_school_group_detail.updateby
     *
     * @mbg.generated
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }
}