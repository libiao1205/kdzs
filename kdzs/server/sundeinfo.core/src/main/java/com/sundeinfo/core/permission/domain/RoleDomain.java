package com.sundeinfo.core.permission.domain;

import com.sundeinfo.foundation.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class RoleDomain extends Entity {
    private String roleName;
    private String roleCode;

}