package com.sundeinfo.core.permission;

import org.springframework.beans.factory.Aware;

import java.util.Map;

public interface PermissionAware extends Aware {
    void setPermissionMap(Map<String, Boolean> map);
}
