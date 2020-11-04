package com.yidiandian.config;

import com.yidiandian.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/3 20:57
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler yidiandianAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler yidiandianAuthenctiationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage( SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(yidiandianAuthenticationSuccessHandler)
                .failureHandler(yidiandianAuthenctiationFailureHandler);
    }
}
