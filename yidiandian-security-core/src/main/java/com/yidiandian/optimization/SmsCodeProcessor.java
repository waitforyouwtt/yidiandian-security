package com.yidiandian.optimization;

import com.yidiandian.validate.SmsCodeSender;
import com.yidiandian.validate.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/28 21:00
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component("smsCodeProcess")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws IOException, ServletRequestBindingException {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter( request.getRequest(), paramName);
        smsCodeSender.sendSmsCode( mobile,validateCode.getCode() );
    }
}
