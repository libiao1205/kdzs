package com.sundeinfo.kd.zs.dao.custom;

import com.sundeinfo.kd.zs.dao.custom.model.SchoolTeacherCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolTeacherCustomMapper {

    List<SchoolTeacherCustom> selectSchoolTeacher(@Param("userId") Integer userId);

}
