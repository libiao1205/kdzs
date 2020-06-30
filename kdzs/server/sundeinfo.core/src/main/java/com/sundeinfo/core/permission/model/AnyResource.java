package com.sundeinfo.core.permission.model;


import com.sundeinfo.foundation.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnyResource extends Entity {

    private String kind;

    private String name;

    private String suffix;

    private String fullPath;
}