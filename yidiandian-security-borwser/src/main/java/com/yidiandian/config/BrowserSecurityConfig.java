package com.yidiandian.config;

import com.yidiandian.authentication.SmsCodeAuthenticationSecurityConfig;
import com.yidiandian.authentication.SmsCodeValidateFilter;
import com.yidiandian.properties.SecurityProperties;
import com.yidiandian.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/14 20:51
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler yidiandianAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler yidiandianAuthenctiationFailureHandler;

    /**
     * 取自demo 项目的application.yml
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService selfUserInfoDetails;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialConfigurer;
    /**
     * 密码加密解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder(  );
    }

    /**
     * 记住我功能
     * @return
     */
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource( dataSource );
        //系统启动时自动创建表
        tokenRepository.setCreateTableOnStartup( false );
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.formLogin()使用自定义表单进行spring security 登录，任何请求都会进行拦截
        //http.httpBasic() 使用httpBasic进行spring security 登录,任意选择一种
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler( yidiandianAuthenctiationFailureHandler );
        validateCodeFilter.setSecurityProperties( securityProperties );
        validateCodeFilter.afterPropertiesSet();

        SmsCodeValidateFilter smsCodeValidateFilter = new SmsCodeValidateFilter();
        smsCodeValidateFilter.setAuthenticationFailureHandler( yidiandianAuthenctiationFailureHandler );
        smsCodeValidateFilter.setSecurityProperties( securityProperties );
        smsCodeValidateFilter.afterPropertiesSet();

            http.addFilterBefore(smsCodeValidateFilter,UsernamePasswordAuthenticationFilter.class  )
                .addFilterBefore( validateCodeFilter, UsernamePasswordAuthenticationFilter.class )
                .formLogin()
               // .loginPage( "/yidiandian-signIn.html" )
                     .loginPage( "/authentication/require" )
                     .loginProcessingUrl( "/security-login" )
                     .successHandler( yidiandianAuthenticationSuccessHandler )
                     .failureHandler( yidiandianAuthenctiationFailureHandler )
        //http.httpBasic()
                     .and()
                     .apply( socialConfigurer )
                     .and()
                .rememberMe()
                .tokenRepository( persistentTokenRepository() )
                .tokenValiditySeconds( securityProperties.getBrowser().getRememberMeSeconds() )
                .userDetailsService( selfUserInfoDetails )
                     .and()
                .authorizeRequests()
               // .antMatchers( "/yidiandian-signIn.html" ).permitAll()
                .antMatchers( "/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        /*"/code/image",
                        "/code/sms",*/
                        "/code/*"
                ).permitAll()
                .anyRequest()
                .authenticated()
        .and()
        .csrf().disable()
            .apply( smsCodeAuthenticationSecurityConfig )
        ;
    }
}
