package com.sundeinfo.kd.zs.dao.model;

import java.util.Date;

public class Area {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.area
     *
     * @mbg.generated
     */
    private String area;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.sort
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.isdelete
     *
     * @mbg.generated
     */
    private Integer isdelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.createtime
     *
     * @mbg.generated
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.createby
     *
     * @mbg.generated
     */
    private Integer createby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.updatetime
     *
     * @mbg.generated
     */
    private Date updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_area.updateby
     *
     * @mbg.generated
     */
    private Integer updateby;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_area
     *
     * @mbg.generated
     */
    public Area(Integer id, String area, Integer sort, Integer isdelete, Date createtime, Integer createby, Date updatetime, Integer updateby) {
        this.id = id;
        this.area = area;
        this.sort = sort;
        this.isdelete = isdelete;
        this.createtime = createtime;
        this.createby = createby;
        this.updatetime = updatetime;
        this.updateby = updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_area
     *
     * @mbg.generated
     */
    public Area() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.id
     *
     * @return the value of m_area.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.id
     *
     * @param id the value for m_area.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.area
     *
     * @return the value of m_area.area
     *
     * @mbg.generated
     */
    public String getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.area
     *
     * @param area the value for m_area.area
     *
     * @mbg.generated
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.sort
     *
     * @return the value of m_area.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.sort
     *
     * @param sort the value for m_area.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.isdelete
     *
     * @return the value of m_area.isdelete
     *
     * @mbg.generated
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.isdelete
     *
     * @param isdelete the value for m_area.isdelete
     *
     * @mbg.generated
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.createtime
     *
     * @return the value of m_area.createtime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.createtime
     *
     * @param createtime the value for m_area.createtime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.createby
     *
     * @return the value of m_area.createby
     *
     * @mbg.generated
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.createby
     *
     * @param createby the value for m_area.createby
     *
     * @mbg.generated
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.updatetime
     *
     * @return the value of m_area.updatetime
     *
     * @mbg.generated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.updatetime
     *
     * @param updatetime the value for m_area.updatetime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_area.updateby
     *
     * @return the value of m_area.updateby
     *
     * @mbg.generated
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_area.updateby
     *
     * @param updateby the value for m_area.updateby
     *
     * @mbg.generated
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }
}