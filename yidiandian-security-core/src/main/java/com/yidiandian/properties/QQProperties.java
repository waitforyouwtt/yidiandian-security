package com.yidiandian.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/1 21:41
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class QQProperties extends SocialProperties {

    //服务提供商
    private String providerId = "qq";
}
