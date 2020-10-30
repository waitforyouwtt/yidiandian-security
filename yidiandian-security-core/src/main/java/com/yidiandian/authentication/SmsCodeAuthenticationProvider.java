package com.yidiandian.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/29 21:47
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    /**
     * 进行身份验证的逻辑
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken token = (SmsCodeAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername( (String) token.getPrincipal() );
        if (Objects.isNull(user)){
            throw new InternalAuthenticationServiceException( "无法读取用户信息" );
        }
        //用户信息 & 用户权限
        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(user,user.getAuthorities()  );
        authenticationToken.setDetails( token.getDetails() );
        return authenticationToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
