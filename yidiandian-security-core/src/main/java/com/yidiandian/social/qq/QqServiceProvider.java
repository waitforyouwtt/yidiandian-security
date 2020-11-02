package com.yidiandian.social.qq;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/31 17:13
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class QqServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    //获取qq 授权码的URL
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    //获取qq 令牌
    private static final String URL_ASSESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QqServiceProvider(String appId,String appSecret) {
        super( new QQOAuth2Template( appId,appSecret,URL_AUTHORIZE,URL_ASSESS_TOKEN ) );
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QqServiceImpl( accessToken,appId );
    }
}
