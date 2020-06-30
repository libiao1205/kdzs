package com.sundeinfo.kd.zs.apis.restful.auth.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sundeinfo.core.authentication.dao.DaoAuthenticationProvider;
import com.sundeinfo.core.permission.strategy.token.AuthenticationResponse;
import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.ResponseCallback;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.define.enums.WeChatMpType;
import com.sundeinfo.kd.zs.dto.auth.wx.WxLoginDTO;
import com.sundeinfo.kd.zs.dto.auth.wx.WxRegisterPhoneDTO;
import com.sundeinfo.kd.zs.dto.auth.wx.WxSessionInfoDTO;
import com.sundeinfo.kd.zs.dto.sys.UserDTO;
import com.sundeinfo.kd.zs.service.auth.UserAuthService;
import com.sundeinfo.kd.zs.service.auth.wx.WxAuthService;
import com.sundeinfo.kd.zs.service.user.UserService;
import com.sundeinfo.kd.zs.utility.StringUtils;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/auth/wx/")
public class WxAuthRestController extends AbstractController<WxAuthRestController> {

    @Autowired
    private WxAuthService wxAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = "/login")
    public ResponseEntity<Result<AuthenticationResponse>> login(@RequestBody WxLoginDTO wxLoginDTO) throws AuthenticationException {
        return requestHandler.doPost(
                new ResponseCallback<AuthenticationResponse>() {
                    String token = "";
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public RequestState invoke() throws Exception {
                        WxSessionInfoDTO wxSessionInfoDto = wxAuthService.jscode2session(wxLoginDTO.getMpType(), wxLoginDTO.getWxCode());
                        if (wxSessionInfoDto == null) {
                            return RequestState.WX_AUTH_ERROR;
                        }
                        UserDTO userDTO = null;
                        if (wxSessionInfoDto.getSession_key() != null) {
                            if (wxSessionInfoDto.getUnionid() != null) {
                                userDTO = userService.findByUnionId(wxSessionInfoDto.getUnionid());
                            } else {
                                if (wxSessionInfoDto.getOpenid() != null) {
                                    userDTO = userService.findByOpenId(wxSessionInfoDto.getOpenid());
                                }
                            }
                            if (userDTO != null) {
                                token = userAuthService.login(userDTO.getUsername(), DaoAuthenticationProvider.getNoEncoderPassword(userDTO.getLoginPassword()));
                            } else {
                                return RequestState.WX_AUTH_NO_USER_ERROR;
                            }
                        } else {
                            return RequestState.WX_AUTH_ERROR;
                        }
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public AuthenticationResponse response() throws Exception {
                        return UserHelper.createJwtAuthenticationResponse(token);
                    }
                }
        );
    }

    @PostMapping(value = "/registerPhone")
    public ResponseEntity<Result<AuthenticationResponse>> registerPhone(@RequestBody WxRegisterPhoneDTO wxRegisterPhoneDTO) throws AuthenticationException {
        return requestHandler.doPost(
                new ResponseCallback<AuthenticationResponse>() {
                    String token = "";
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public RequestState invoke() throws Exception {
                        WxSessionInfoDTO wxSessionInfoDto = wxAuthService.jscode2session(wxRegisterPhoneDTO.getMpType(), wxRegisterPhoneDTO.getWxCode());
                        if (wxSessionInfoDto.getSession_key() != null) {
                            String json = wxAuthService.decryptData(wxSessionInfoDto.getSession_key(),wxRegisterPhoneDTO.getEncryptedData(), wxRegisterPhoneDTO.getIv());
                            if (json == null) {
                                return RequestState.WX_AUTH_ERROR;
                            }
                            JSONObject jsonObject =  JSON.parseObject(json);
                            String phone = jsonObject.getString("purePhoneNumber");
                            if (StringUtils.isNotBlank(phone)) {
                                UserDTO userDTO = userService.findByPhone(phone);
                                // 如果用户存在
                                if (userDTO == null) {
                                    // 招生数据填报小程序 无注册功能，注册功能好比登录
                                    if (wxRegisterPhoneDTO.getMpType() == WeChatMpType.COUNTING) {
                                        return RequestState.WX_AUTH_NO_USER_ERROR;
                                    } else {
                                        // 进行注册，其他小程序进行封装
                                    }
                                } else {
                                    // 如果用户存在则进行登录
                                    token = userAuthService.login(userDTO.getUsername(), DaoAuthenticationProvider.getNoEncoderPassword(userDTO.getLoginPassword()));
                                }
                            } else {
                                return RequestState.WX_AUTH_ERROR;
                            }
                        } else {
                            return RequestState.WX_AUTH_ERROR;
                        }
                        return RequestState.SUCCESS;
                    }

                    @Override
                    public AuthenticationResponse response() throws Exception {
                        return UserHelper.createJwtAuthenticationResponse(token);
                    }
                }
        );
    }

}
