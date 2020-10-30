package com.yidiandian;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/19 20:35
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class BrowserProperties {

    private String loginPage = "/yidiandian-signIn.html";
    private LoginResponseType loginType = LoginResponseType.JSON;
    //配置的是秒数：1分=60秒，60秒[1分钟]*60[分钟] = 3600秒 [1小时]
    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
