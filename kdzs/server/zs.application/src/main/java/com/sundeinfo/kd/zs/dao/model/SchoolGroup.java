package com.sundeinfo.kd.zs.dao.model;

import java.util.Date;

public class SchoolGroup {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.sort
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.isdelete
     *
     * @mbg.generated
     */
    private Boolean isdelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.createtime
     *
     * @mbg.generated
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.createby
     *
     * @mbg.generated
     */
    private Integer createby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.updatetime
     *
     * @mbg.generated
     */
    private Date updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_school_group.updateby
     *
     * @mbg.generated
     */
    private Integer updateby;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school_group
     *
     * @mbg.generated
     */
    public SchoolGroup(Integer id, Integer sort, String name, Boolean isdelete, Date createtime, Integer createby, Date updatetime, Integer updateby) {
        this.id = id;
        this.sort = sort;
        this.name = name;
        this.isdelete = isdelete;
        this.createtime = createtime;
        this.createby = createby;
        this.updatetime = updatetime;
        this.updateby = updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school_group
     *
     * @mbg.generated
     */
    public SchoolGroup() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.id
     *
     * @return the value of m_school_group.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.id
     *
     * @param id the value for m_school_group.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.sort
     *
     * @return the value of m_school_group.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.sort
     *
     * @param sort the value for m_school_group.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.name
     *
     * @return the value of m_school_group.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.name
     *
     * @param name the value for m_school_group.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.isdelete
     *
     * @return the value of m_school_group.isdelete
     *
     * @mbg.generated
     */
    public Boolean getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.isdelete
     *
     * @param isdelete the value for m_school_group.isdelete
     *
     * @mbg.generated
     */
    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.createtime
     *
     * @return the value of m_school_group.createtime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.createtime
     *
     * @param createtime the value for m_school_group.createtime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.createby
     *
     * @return the value of m_school_group.createby
     *
     * @mbg.generated
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.createby
     *
     * @param createby the value for m_school_group.createby
     *
     * @mbg.generated
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.updatetime
     *
     * @return the value of m_school_group.updatetime
     *
     * @mbg.generated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.updatetime
     *
     * @param updatetime the value for m_school_group.updatetime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_school_group.updateby
     *
     * @return the value of m_school_group.updateby
     *
     * @mbg.generated
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_school_group.updateby
     *
     * @param updateby the value for m_school_group.updateby
     *
     * @mbg.generated
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }
}