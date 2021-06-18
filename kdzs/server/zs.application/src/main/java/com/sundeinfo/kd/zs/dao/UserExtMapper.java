package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.User;
import com.sundeinfo.kd.zs.dao.model.UserExample;
import com.sundeinfo.kd.zs.dto.sys.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserExtMapper {

    int insertSelective(User record);

    List<UserDTO> selectByPrimaryKey(String key);

    List<UserDTO> selectByPrimaryKeyOne(Integer id);

}