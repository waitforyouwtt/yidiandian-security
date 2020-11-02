package com.yidiandian.social.qq;

import com.yidiandian.properties.QQProperties;
import com.yidiandian.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/1 23:48
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Configuration
//系统只有在配置过appId,providerId,appSecret,这个才生效。反之，就不生效
@ConditionalOnProperty(prefix = "yidiandian.security.social.qq",name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocialProperties().getQqProperties();
        return new QQConnectionFactory( qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret() );
    }
}
