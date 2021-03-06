package com.sundeinfo.kd.zs.dao.model;

public class UserRoleKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user_role.userId
     *
     * @mbg.generated
     */
    private Integer userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user_role.roleId
     *
     * @mbg.generated
     */
    private Integer roleid;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user_role
     *
     * @mbg.generated
     */
    public UserRoleKey(Integer userid, Integer roleid) {
        this.userid = userid;
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user_role
     *
     * @mbg.generated
     */
    public UserRoleKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user_role.userId
     *
     * @return the value of u_user_role.userId
     *
     * @mbg.generated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user_role.userId
     *
     * @param userid the value for u_user_role.userId
     *
     * @mbg.generated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user_role.roleId
     *
     * @return the value of u_user_role.roleId
     *
     * @mbg.generated
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user_role.roleId
     *
     * @param roleid the value for u_user_role.roleId
     *
     * @mbg.generated
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}