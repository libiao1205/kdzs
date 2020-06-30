package com.sundeinfo.core.permission.domain;

import com.sundeinfo.foundation.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AdminDomain extends Entity {

    private Integer userId;

}