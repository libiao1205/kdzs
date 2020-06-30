package com.sundeinfo.kd.zs.dao.model;

public class RolePermissionKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_role_permission.roleId
     *
     * @mbg.generated
     */
    private Integer roleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_role_permission.permissionId
     *
     * @mbg.generated
     */
    private Integer permissionid;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_role_permission
     *
     * @mbg.generated
     */
    public RolePermissionKey(Integer roleid, Integer permissionid) {
        this.roleid = roleid;
        this.permissionid = permissionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_role_permission
     *
     * @mbg.generated
     */
    public RolePermissionKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_role_permission.roleId
     *
     * @return the value of u_role_permission.roleId
     *
     * @mbg.generated
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_role_permission.roleId
     *
     * @param roleid the value for u_role_permission.roleId
     *
     * @mbg.generated
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_role_permission.permissionId
     *
     * @return the value of u_role_permission.permissionId
     *
     * @mbg.generated
     */
    public Integer getPermissionid() {
        return permissionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_role_permission.permissionId
     *
     * @param permissionid the value for u_role_permission.permissionId
     *
     * @mbg.generated
     */
    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}