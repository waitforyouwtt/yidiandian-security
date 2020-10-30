package com.yidiandian.utils;

import com.yidiandian.validate.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/26 21:44
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component("smsValidateCodeSender")
@Slf4j
public class SelfSmsValidateCodeSender implements SmsCodeSender {

    /**
     * 手机发送验证码
     *
     * @param mobile
     * @param code
     */
    @Override
    public void sendSmsCode(String mobile, String code) {
        log.info( "用户用自己的短信供应商发送短信验证码：{}，{}" ,mobile,code);
    }
}
