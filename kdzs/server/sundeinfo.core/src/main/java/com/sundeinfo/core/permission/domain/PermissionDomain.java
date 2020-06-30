package com.sundeinfo.core.permission.domain;

import com.sundeinfo.foundation.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class PermissionDomain extends Entity {

    String code;

    String function;

    String action;

    String kind;

    int value;
}