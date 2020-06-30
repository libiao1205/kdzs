package com.sundeinfo.foundation.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Entity implements Identity,Serializable{
    private Integer id;
}
