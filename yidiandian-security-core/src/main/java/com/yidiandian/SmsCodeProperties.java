package com.yidiandian;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/22 21:46
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    private String url;

    public int getLength() {
        return length;
    }
    public void setLength(int lenght) {
        this.length = lenght;
    }
    public int getExpireIn() {
        return expireIn;
    }
    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
