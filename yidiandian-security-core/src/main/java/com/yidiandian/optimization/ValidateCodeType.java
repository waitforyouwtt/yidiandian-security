package com.yidiandian.optimization;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/28 19:58
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public enum ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS{
       @Override
       public String getParamNameOnValidate(){
           return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    IMAGE{
        @Override
        public String getParamNameOnValidate(){
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    public abstract String getParamNameOnValidate();
}
