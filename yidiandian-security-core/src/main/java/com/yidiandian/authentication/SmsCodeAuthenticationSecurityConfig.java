package com.yidiandian.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/29 22:39
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private UserDetailsService userDetails;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure( http );
        SmsCodeAuthenticationFilter filter = new SmsCodeAuthenticationFilter();
        filter.setAuthenticationManager( http.getSharedObject( AuthenticationManager.class ) );
        filter.setAuthenticationSuccessHandler( successHandler );
        filter.setAuthenticationFailureHandler( failureHandler );

        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setUserDetailsService( userDetails );
        http.authenticationProvider( provider )
        .addFilterAfter( filter, UsernamePasswordAuthenticationFilter.class );
    }
}
