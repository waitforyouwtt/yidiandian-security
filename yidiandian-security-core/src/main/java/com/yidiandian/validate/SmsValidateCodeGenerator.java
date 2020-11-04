package com.yidiandian.validate;

import com.yidiandian.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/26 21:14
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    SecurityProperties securityProperties;

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 图形验证码
     *
     * @param request
     * @return
     */
    @Override
    public ValidateCode generateCode(ServletWebRequest request) {
        String random = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyz0123456789");
        ValidateCode smsCode = new ValidateCode( random,securityProperties.getCode().getSms().getExpireIn() );
        return smsCode;
    }
}
