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

    private String phoneNo;

    private String loginName;

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

}