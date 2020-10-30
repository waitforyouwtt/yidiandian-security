package com.yidiandian.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/26 21:15
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */

public interface SmsCodeGenerator {
    public ValidateCode generateCode(ServletWebRequest request);
}
