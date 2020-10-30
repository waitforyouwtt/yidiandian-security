package com.yidiandian.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/25 12:47
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface ValidateCodeGenerator {
    /**
     * 图形验证码
     * @param request
     * @return
     */
    ValidateCode generateCode(ServletWebRequest request);
}
