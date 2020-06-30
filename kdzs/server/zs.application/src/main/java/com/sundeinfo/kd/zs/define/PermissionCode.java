package com.sundeinfo.kd.zs.define;

import com.sundeinfo.kd.zs.utility.StringUtils;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 权限标识定义
 */
public class PermissionCode {
    public static final String SCHOOL_RECRUIT_ADD = "school:recruit:counting:add";

    public static final String SCHOOL_RECRUIT_VIEW = "school:recruit:counting:view";

    public static final String SCHOOL_RECRUIT_VIEW_ALL = "school:recruit:counting:viewall";

    public static boolean hasSchoolRecruitAdd() {
        return checkPermissionCode(SCHOOL_RECRUIT_ADD);
    }

    public static boolean hasSchoolRecruitView() {
        return checkPermissionCode(SCHOOL_RECRUIT_VIEW);
    }

    public static boolean hasSchoolRecruitViewAll() {
        return checkPermissionCode(SCHOOL_RECRUIT_VIEW_ALL);
    }

    private static boolean checkPermissionCode(String code) {
        return UserHelper.getCurrentUserPermissions().stream().filter(item -> item.equals(code)).toArray().length > 0;
    }

}
