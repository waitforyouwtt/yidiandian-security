package com.yidiandian.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/1 21:41
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
