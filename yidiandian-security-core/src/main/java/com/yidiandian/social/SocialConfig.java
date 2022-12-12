package com.yidiandian.social;

import com.yidiandian.config.YidiandianSpringSocialConfigurer;
import com.yidiandian.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/31 18:19
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
       // return super.getUsersConnectionRepository( connectionFactoryLocator );
        //参数说明：数据源 & 查找qqFactoryLocator(找到适合的工厂) & 插入到库里的数据加解密
        //可以加前缀的哦
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText() );
        repository.setTablePrefix( "tb_" );
        if (connectionSignUp != null){
            repository.setConnectionSignUp( connectionSignUp );
        }
        return repository;

        //return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText() );
    }

    @Bean
    public SpringSocialConfigurer socialConfigurer(){
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
       // return new SpringSocialConfigurer();
        YidiandianSpringSocialConfigurer config = new YidiandianSpringSocialConfigurer(filterProcessesUrl);
        //找不到用户时，自动注册
        config.signupUrl( securityProperties.getBrowser().getSignUpUrl() );
        return config;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils( connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator) );
    }
}