package com.sundeinfo.core.permission.strategy.token;

import com.sundeinfo.core.permission.domain.OrganizationDomain;
import com.sundeinfo.core.permission.domain.PermissionDomain;
import com.sundeinfo.core.permission.domain.RoleDomain;
import com.sundeinfo.core.permission.domain.RolePermissionDomain;

import java.io.Serializable;
import java.util.List;

public class AuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private String captcha;

    private String name;

    private String token;

    private int logined;

    private String operation;

    private List<OrganizationDomain> organizations;

    private List<RoleDomain> roleDomains;

    public AuthenticationResponse() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLogined() {
        return logined;
    }

    public void setLogined(int logined) {
        this.logined = logined;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<OrganizationDomain> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationDomain> organizations) {
        this.organizations = organizations;
    }

    public List<RoleDomain> getRoleDomains() {
        return roleDomains;
    }

    public void setRoleDomains(List<RoleDomain> roleDomains) {
        this.roleDomains = roleDomains;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "captcha='" + captcha + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", logined=" + logined +
                ", operation='" + operation + '\'' +
                '}';
    }
}
