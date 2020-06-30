package com.sundeinfo.kd.zs.apis.restful.auth;


import com.sundeinfo.core.permission.model.AnyUser;
import com.sundeinfo.core.permission.strategy.token.AuthenticationResponse;
import com.sundeinfo.core.permission.utility.AuthorityCreater;
import com.sundeinfo.core.permission.utility.TokenResource;
import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.ResponseCallback;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.dto.auth.LoginDTO;
import com.sundeinfo.kd.zs.service.auth.UserAuthService;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/auth/")
public class AuthRestController extends AbstractController<AuthRestController> {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private TokenResource tokenResource;

    @PostMapping(value = "/login")
    public ResponseEntity<Result<AuthenticationResponse>> createToken(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
        return requestHandler.doPost(
                new ResponseCallback<AuthenticationResponse>() {
                    String token = "";
                    @Override
                    public RequestState doCheck() {
                        if (!StringUtils.hasText(loginDTO.getUsername()) || !StringUtils.hasText(loginDTO.getPassword())){
                            return RequestState.PARAM_USELESS;
                        }
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() throws Exception {
                        token = userAuthService.login(loginDTO.getUsername(), loginDTO.getPassword());

                        if(StringUtils.hasText(token)){
                            return RequestState.SUCCESS;
                        }
                        else{
                            return RequestState.PARAM_USELESS_USERNAME_PASSWORD;
                        }
                    }
                    @Override
                    public AuthenticationResponse response() throws Exception {
                        return UserHelper.createJwtAuthenticationResponse(token);
                    }
                }
        );
    }

}