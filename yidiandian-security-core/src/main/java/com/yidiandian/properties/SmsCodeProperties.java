package com.yidiandian.properties;

import lombok.Data;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/22 21:46
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;
    private String url;
}
