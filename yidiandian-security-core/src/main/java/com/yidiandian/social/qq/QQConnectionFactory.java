package com.yidiandian.social.qq;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/31 18:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId,  String appId,String appSecret) {
        //参数说明：提供商的唯一标识，
        super( providerId, new QqServiceProvider( appId,appSecret ),new QQAdapter() );
    }
}
