package com.sundeinfo.core.authentication.dao;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class DaoAuthenticationProvider extends org.springframework.security.authentication.dao.DaoAuthenticationProvider {

    public static final String PASSWORD_NO_ENCODER = "#_no_encoder_#";

    public static String getNoEncoderPassword(String password) {
        return PASSWORD_NO_ENCODER + password;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object credentials = authentication.getCredentials();
        if (credentials != null && credentials.toString().startsWith(PASSWORD_NO_ENCODER)) {
            if (getNoEncoderPassword(userDetails.getPassword()).equals(credentials.toString())) {
                return;
            }
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
