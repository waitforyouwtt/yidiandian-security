package com.yidiandian.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/24 7:56
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super( msg);
    }
}
