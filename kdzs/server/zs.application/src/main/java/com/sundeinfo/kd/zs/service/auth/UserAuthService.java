package com.sundeinfo.kd.zs.service.auth;

import com.sundeinfo.core.permission.model.AnyUser;
import com.sundeinfo.core.permission.strategy.token.TokenUtil;
import com.sundeinfo.core.permission.utility.AuthenticationGetter;
import com.sundeinfo.core.permission.utility.TokenResource;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.UserMapper;
import com.sundeinfo.kd.zs.dao.model.User;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.apache.commons.codec.binary.Hex.encodeHex;

/**
 * Created by FzzF on 2019/3/1.
 */
@Service
public class UserAuthService extends AbstractService<UserAuthService> {

    @Autowired
    private AuthenticationGetter authenticationGetter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private TokenResource tokenResource;

    @Resource
    private UserMapper userMapper;

    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AnyUser anyUser = (AnyUser)authenticationGetter.getAuthentication(username).getPrincipal();
        final String token = tokenUtil.generateToken(anyUser);
        return token;
    }

    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenResource.getTokenheader().length());
        String username = tokenUtil.getUsernameFromToken(token);
        AnyUser anyUser = (AnyUser)authenticationGetter.getAuthentication(username).getPrincipal();
        if (tokenUtil.canTokenBeRefreshed(token, anyUser.getUser().getLastPasswordResetDate())){
            return tokenUtil.refreshToken(token);
        }
        return null;
    }

    public int updateUser(String rePassword,Integer userId) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] arr = digest.digest(rePassword.getBytes());
        User user = new User();
        user.setId(userId);
        String str = new String(encodeHex(arr));
        user.setPassword(str);
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
