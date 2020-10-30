package com.yidiandian;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/19 20:34
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ConfigurationProperties(prefix = "yidiandian.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
