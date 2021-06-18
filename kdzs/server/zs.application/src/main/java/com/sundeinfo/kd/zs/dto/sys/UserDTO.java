package com.sundeinfo.kd.zs.dto.sys;

import com.sundeinfo.core.permission.domain.UserDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends UserDomain implements Serializable {

    private Integer id;

    private Integer lineNumber;

    private String phoneNo;

    private String loginName;

    private String name;

    private Integer roleId;

    private Integer[] roleIds;

    private String roleName;

    private String schoolName;

    private Integer schoolId;


    public void setLoginname(String loginname) {
        super.setUsername(loginname);
        this.loginName = loginname;
    }

    private String loginPassword;
    public void setLoginPassword(String loginPassword) {
        super.setPassword(loginPassword);
        this.loginPassword = loginPassword;
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
        this.loginName = username;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.loginPassword = password;
    }

    public UserDTO() {
    }

    public UserDTO(Integer id, String loginName, String name, String roleName, String schoolName,Integer schoolId) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.roleName = roleName;
        this.schoolName = schoolName;
        this.schoolId = schoolId;
    }
    public UserDTO(Integer id, String loginName, String name, Integer roleId, String schoolName,Integer schoolId) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.roleId = roleId;
        this.schoolName = schoolName;
        this.schoolId = schoolId;
    }
}