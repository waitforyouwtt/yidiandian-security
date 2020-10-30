package com.yidiandian.validate;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/26 20:50
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface SmsCodeSender {

    /**
     * 手机发送验证码
     * @param mobile
     * @param code
     */
    void sendSmsCode(String mobile, String code);
}
