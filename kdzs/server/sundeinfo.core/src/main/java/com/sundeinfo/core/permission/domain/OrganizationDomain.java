package com.sundeinfo.core.permission.domain;

import com.sundeinfo.foundation.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class OrganizationDomain extends Entity {
    private String name;
    private String shortName;
}