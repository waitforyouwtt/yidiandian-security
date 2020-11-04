package com.yidiandian.properties;

import lombok.Data;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/1 23:36
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();
}
