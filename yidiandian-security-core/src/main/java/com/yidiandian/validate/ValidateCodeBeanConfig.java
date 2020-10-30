package com.yidiandian.validate;

import com.yidiandian.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/25 16:54
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageValidateCodeGenerator codeGenerator = new ImageValidateCodeGenerator(  );
         codeGenerator.setSecurityProperties( securityProperties);
         return codeGenerator;
    }

    /**
     * 手机短信配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsValidateCodeSender")
    public SmsCodeSender smsCodeSender(){
        DefaultSmsCodeSender smsGenerator = new DefaultSmsCodeSender(  );
        return smsGenerator;
    }
}
