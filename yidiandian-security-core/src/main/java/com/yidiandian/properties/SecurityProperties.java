package com.yidiandian.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/19 20:34
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ConfigurationProperties(prefix = "yidiandian.security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();
}
