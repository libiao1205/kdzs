package com.sundeinfo.core.permission.domain;

import com.sundeinfo.foundation.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class UserDomain extends Entity {

    private String name;

    private String username;

    private String password;

    private Date lastPasswordResetDate;
}