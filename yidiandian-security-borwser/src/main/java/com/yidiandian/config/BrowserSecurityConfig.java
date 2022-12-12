package com.yidiandian.config;

import com.yidiandian.authentication.SmsCodeAuthenticationSecurityConfig;
import com.yidiandian.constants.SecurityConstants;
import com.yidiandian.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    SecurityProperties securityProperties;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * 取自demo 项目的application.yml
     */
    @Autowired
    private DataSource dataSource;
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
        //UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);

        applyPasswordAuthenticationConfig( http );
        http.apply( validateCodeSecurityConfig )
                .and()
                .apply( smsCodeAuthenticationSecurityConfig )
                .and()
                .apply( socialSecurityConfig )
                .and()
                .rememberMe()
                .tokenRepository( persistentTokenRepository() )
                .tokenValiditySeconds( securityProperties.getBrowser().getRememberMeSeconds() )
                .userDetailsService(userDetailsService )
               /* .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()*/
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                       /* securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",*/
                        "/user/regist"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
        ;

    }
}
