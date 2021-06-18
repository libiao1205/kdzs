package com.sundeinfo.kd.zs.apis.user;

import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.GetCallback;
import com.sundeinfo.foundation.request.PostCallback;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.ResponseCallback;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.apis.restful.school.recruit.RecruitPlanController;
import com.sundeinfo.kd.zs.dto.sys.UserDTO;
import com.sundeinfo.kd.zs.service.user.UserService;
import com.sundeinfo.kd.zs.utility.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/user")
public class userController extends AbstractController<userController> {

    @Autowired
    UserService userService;

    @PostMapping(value = "/saveUser")
    public ResponseEntity<Result> saveUser(@RequestBody UserDTO userDTO){
        return requestHandler.doPost(
                new PostCallback() {

                    @Override
                    public RequestState doCheck() throws Exception {
                        if (StringUtils.isBlank(userDTO.getLoginName()) ||
                            StringUtils.isBlank(userDTO.getName())||
                        userDTO.getRoleIds() == null ) {
                            return RequestState.PARAM_NULL;
                        }
                        //MessageDigest digest = MessageDigest.getInstance("md5");
                        //digest.digest("111111".getBytes());
                        userDTO.setPassword("96e79218965eb72c92a549dd5a330112");
                        for(int i = 0; i< userDTO.getRoleIds().length; i++){
                            if(userDTO.getRoleIds()[i] != 1 && userDTO.getSchoolId() == null){
                                return RequestState.PARAM_NULL;
                            }
                            if(userDTO.getRoleIds()[i] == 1){
                                userDTO.setSchoolId(null);
                                break;
                            }
                        }
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public RequestState invoke() throws Exception {
                        UserDTO dto = userService.findByUsername(userDTO.getLoginName());
                        if(dto != null){
                         return RequestState.PARAM_USELESS_PHONE;
                        }
                        int i = userService.save(userDTO);
                        if (i > 0) {
                            return RequestState.SUCCESS;
                        } else {
                            return RequestState.DB_INSERT_ERROR;
                        }
                    }
                });
    }
    @PostMapping(value = "/updateUser")
    public ResponseEntity<Result> updateUser(@RequestBody UserDTO userDTO){
        return requestHandler.doPost(
                new PostCallback() {

                    @Override
                    public RequestState doCheck() throws Exception {
                        if (userDTO.getRoleIds() == null ||
                                userDTO.getId() == null) {
                            return RequestState.PARAM_NULL;
                        }
                        for(int i = 0; i< userDTO.getRoleIds().length; i++){
                            if(userDTO.getRoleIds()[i] != 1 && userDTO.getSchoolId() == null){
                                return RequestState.PARAM_NULL;
                            }
                            if(userDTO.getRoleIds()[i] == 1){
                                userDTO.setSchoolId(null);
                                break;
                            }
                        }
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() throws Exception {
                        int i = userService.update(userDTO);
                        if (i > 0) {
                            return RequestState.SUCCESS;
                        } else {
                            return RequestState.DB_UPDATE_ERROR;
                        }
                    }
                });
    }
    @GetMapping(value = "/findAllUser")
    public ResponseEntity<Result<List<UserDTO>>> findAll(@RequestParam String key){
        return requestHandler.doPost(
                new ResponseCallback<List<UserDTO>>() {
                    List<UserDTO> userDTOList = null;
                    @Override
                    public RequestState doCheck() throws Exception {
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public RequestState invoke() throws Exception {
                        userDTOList = userService.findAllUser(key);
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public List<UserDTO> response() throws Exception {
                        return userDTOList;
                    }
                });
    }
    @GetMapping(value = "/findOneUser")
    public ResponseEntity<Result<UserDTO>> findOne(@RequestParam Integer id){
        return requestHandler.doPost(
                new ResponseCallback<UserDTO>() {
                    UserDTO userDTO = null;
                    @Override
                    public RequestState doCheck() throws Exception {
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public RequestState invoke() throws Exception {
                        userDTO = userService.findOneUser(id);
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public UserDTO response() throws Exception {
                        return userDTO;
                    }
                });
    }
    @GetMapping(value = "/deleteUser")
    public ResponseEntity<Result> deleteUser(@RequestParam Integer id){
        return requestHandler.doGet(
                new GetCallback() {
                    List<UserDTO> userDTOList = null;
                    @Override
                    public RequestState doCheck() {
                        if(id == null || id == 0){
                            return RequestState.PARAM_NULL;
                        }
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public RequestState invoke() throws Exception {
                        int i = userService.delete(id);
                        if(i == 0){
                            return RequestState.SUCCESS;
                        }else{
                            return RequestState.DB_DELETE_ERROR;
                        }
                    }
                });
    }
}