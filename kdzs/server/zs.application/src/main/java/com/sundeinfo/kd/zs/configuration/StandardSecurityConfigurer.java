package com.sundeinfo.kd.zs.configuration;


//import com.sundeinfo.security.strategy.AnyUserService;
//import com.sundeinfo.security.strategy.token.AuthenticationTokenFilter;

import com.sundeinfo.core.authentication.dao.DaoAuthenticationProvider;
import com.sundeinfo.core.permission.strategy.AnyUserService;
import com.sundeinfo.core.permission.strategy.token.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StandardSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AnyUserService anyUserService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(anyUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public MessageDigestPasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new AuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 删除csrf
                .csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/kdzs/static/**",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // 登录页面
                .antMatchers("/kdzs/login/**").permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/kdzs/api/auth/**").permitAll()

                .antMatchers("/kdzs/api/school/recruit/getExcel").permitAll()

                .antMatchers("/images/file").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // session管理
        httpSecurity
                .sessionManagement().sessionAuthenticationStrategy(new SessionAuthenticationStrategy() {
                @Override
                public void onAuthentication(Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SessionAuthenticationException {
                    // 空实现
                }
        });
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        httpSecurity.headers().frameOptions().disable();
    }
}

