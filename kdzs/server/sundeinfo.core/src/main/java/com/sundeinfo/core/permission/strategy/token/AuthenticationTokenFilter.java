package com.sundeinfo.core.permission.strategy.token;

import com.sundeinfo.core.permission.utility.AuthenticationGetter;
import com.sundeinfo.core.permission.utility.TokenResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenResource tokenResource;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AuthenticationGetter authenticationGetter;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // logger.info("SESSIONID:[" + request.getSession().getId()+ "]  URI:[" + request.getRequestURI()  + "]");
        if (request.getRequestURI().startsWith("/kdzs/static") ||
                request.getRequestURI().startsWith("kdzs/api/auth") ) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(tokenResource.getRequestheader());
        if (authHeader == null) {
            try {
                authHeader = request.getSession().getAttribute(tokenResource.getRequestheader()).toString();
            } catch (Exception e) {
            }
        }
        if (authHeader != null && authHeader.startsWith(tokenResource.getTokenheader())) {
            final String authToken = authHeader.substring(tokenResource.getTokenheader().length());
            String username = tokenUtil.getUsernameFromToken(authToken);
            logger.info("------doFilterInternal:username:[" + username + "] check authentication------ : ");
            if (StringUtils.isNotBlank(authToken) && StringUtils.isNotBlank(username)) {
                Authentication authentication = authenticationGetter.getAuthentication(authToken, username);
            }

        }
        chain.doFilter(request, response);
    }
}

