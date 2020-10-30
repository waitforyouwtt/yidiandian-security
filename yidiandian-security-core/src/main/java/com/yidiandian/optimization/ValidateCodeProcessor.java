package com.yidiandian.optimization;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/27 22:07
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    /**
     * 生成校验码
     * @param request
     */
    void create(ServletWebRequest request) throws IOException, ServletRequestBindingException;

    /**
     * 校验验证码
     * @param request
     */
    void validate(ServletWebRequest request);
}
