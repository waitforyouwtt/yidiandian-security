package com.yidiandian.validate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/26 20:51
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void sendSmsCode(String mobile, String code) {
        log.info("发送的短信验证码的手机是：{}，验证码是：{}",mobile,code);
    }
}
