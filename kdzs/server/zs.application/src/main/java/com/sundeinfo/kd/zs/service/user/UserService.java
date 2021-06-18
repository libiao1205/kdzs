package com.sundeinfo.kd.zs.service.user;

import com.sundeinfo.core.permission.service.UserDomainService;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.*;
import com.sundeinfo.kd.zs.dao.model.*;
import com.sundeinfo.kd.zs.dto.sys.UserDTO;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService extends AbstractService<UserService> implements UserDomainService<UserService, UserDTO> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserExtMapper userExtMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private SchoolTeacherMapper schoolTeacherMapper;

    @Override
    public UserDTO createNewUser() {
        return null;
    }

    @Override
    //@Cacheable(value = "UserByName" ,key = "#username")
    public UserDTO findByUsername(String username) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() ==0) {
            return null;
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setPhoneNo(userList.get(0).getPhone());
            userDTO.setId(userList.get(0).getId());
            userDTO.setUsername(username);
            userDTO.setName(userList.get(0).getName());
            userDTO.setPassword(userList.get(0).getPassword());
            return userDTO;
        }
    }

    public UserDTO findByUnionId(String unionId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUnionidEqualTo(unionId);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() ==0) {
            return null;
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setPhoneNo(userList.get(0).getPhone());
            userDTO.setId(userList.get(0).getId());
            userDTO.setUsername(userList.get(0).getUsername());
            userDTO.setName(userList.get(0).getName());
            userDTO.setPassword(userList.get(0).getPassword());
            return userDTO;
        }
    }

    public UserDTO findByOpenId(String openId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andOpenidEqualTo(openId);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() ==0) {
            return null;
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setPhoneNo(userList.get(0).getPhone());
            userDTO.setId(userList.get(0).getId());
            userDTO.setUsername(userList.get(0).getUsername());
            userDTO.setName(userList.get(0).getName());
            userDTO.setPassword(userList.get(0).getPassword());
            return userDTO;
        }
    }

    public UserDTO findByPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andPhoneEqualTo(phone);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() ==0) {
            return null;
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setPhoneNo(userList.get(0).getPhone());
            userDTO.setId(userList.get(0).getId());
            userDTO.setUsername(userList.get(0).getUsername());
            userDTO.setName(userList.get(0).getName());
            userDTO.setPassword(userList.get(0).getPassword());
            return userDTO;
        }
    }
    @Transactional
    public int save(UserDTO userDTO){
        try {
            User recordUser = new User();
            recordUser.setPhone(userDTO.getLoginName());
            recordUser.setName(userDTO.getName());
            recordUser.setUsername(userDTO.getLoginName());
            recordUser.setPassword(userDTO.getPassword());
            Integer userId = UserHelper.getCurrentUserId();
            recordUser.setCreatetime(new Date());
            recordUser.setUpdatetime(new Date());
            recordUser.setCreateby(userId);
            recordUser.setUpdateby(userId);
            userExtMapper.insertSelective(recordUser);
            UserRoleKey recordUserRoleKey = new UserRoleKey();
            recordUserRoleKey.setUserid(recordUser.getId());
            for(int i = 0; i < userDTO.getRoleIds().length; i++){
                recordUserRoleKey.setRoleid(userDTO.getRoleIds()[i]);
                userRoleMapper.insertSelective(recordUserRoleKey);
            }
            if(userDTO.getSchoolId() != null){
                SchoolTeacher recordSchoolTeacher = new SchoolTeacher();
                recordSchoolTeacher.setUserid(recordUser.getId());
                recordSchoolTeacher.setSchoolid(userDTO.getSchoolId());
                schoolTeacherMapper.insertSelective(recordSchoolTeacher);
            }
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
    }
    @Transactional
    public int update(UserDTO userDTO){
        try{
            UserRoleKey recordUserRoleKey = new UserRoleKey();
            recordUserRoleKey.setUserid(userDTO.getId());
            UserRoleExample example = new UserRoleExample();
            UserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUseridEqualTo(userDTO.getId());
            userRoleMapper.deleteByExample(example);

            for(int i = 0; i < userDTO.getRoleIds().length; i++){
                recordUserRoleKey.setRoleid(userDTO.getRoleIds()[i]);
                userRoleMapper.insertSelective(recordUserRoleKey);
            }

            SchoolTeacherExample teacherExample = new SchoolTeacherExample();
            SchoolTeacherExample.Criteria teacherCriteria = teacherExample.createCriteria();
            teacherCriteria.andUseridEqualTo(userDTO.getId());
            schoolTeacherMapper.deleteByExample(teacherExample);
            if(userDTO.getSchoolId() != null){
                SchoolTeacher recordSchoolTeacher = new SchoolTeacher();
                recordSchoolTeacher.setUserid(userDTO.getId());
                recordSchoolTeacher.setSchoolid(userDTO.getSchoolId());
                schoolTeacherMapper.insertSelective(recordSchoolTeacher);
            }
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
    }
    @Transactional
    public int delete(Integer id){
        try{
            UserRoleExample example = new UserRoleExample();
            UserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUseridEqualTo(id);
            List<UserRoleKey> userRoleKeyList = userRoleMapper.selectByExample(example);
            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserid(id);
            if(userRoleKeyList != null && userRoleKeyList.size() > 0){
                userRoleKeyList.forEach(dto ->{
                    userRoleKey.setRoleid(dto.getRoleid());
                    userRoleMapper.deleteByPrimaryKey(userRoleKey);
                });
            }

            SchoolTeacherExample exampleSchool = new SchoolTeacherExample();
            exampleSchool.createCriteria().andUseridEqualTo(id);
            criteria.andUseridEqualTo(id);
            List<SchoolTeacher> schoolTeacherList = schoolTeacherMapper.selectByExample(exampleSchool);
            if(schoolTeacherList != null && schoolTeacherList.size() > 0){
                schoolTeacherMapper.deleteByPrimaryKey(schoolTeacherList.get(0).getId());
            }

            userMapper.deleteByPrimaryKey(id);
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
    }
    public List<UserDTO> findAllUser(String key){
        List<UserDTO> userDTOList = userExtMapper.selectByPrimaryKey(key);
        for(int i = 0; i < userDTOList.size(); i++){
            userDTOList.get(i).setLineNumber(i+1);
        }
        return userDTOList;
    }

    public UserDTO findOneUser(Integer id){
        List<UserDTO> userDTOList = userExtMapper.selectByPrimaryKeyOne(id);
        UserDTO userDto = new UserDTO();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < userDTOList.size(); i++){
            UserDTO dto = userDTOList.get(i);
            userDto.setId(dto.getId());
            userDto.setName(dto.getName());
            userDto.setLoginname(dto.getLoginName());
            userDto.setPassword(dto.getPassword());
            userDto.setSchoolId(dto.getSchoolId());
            userDto.setSchoolName(dto.getSchoolName());
            list.add(dto.getRoleId());
        };
        Integer[] roles = new Integer[list.size()];
        list.toArray(roles);
        userDto.setRoleIds(roles);
        return userDto;
    }
}