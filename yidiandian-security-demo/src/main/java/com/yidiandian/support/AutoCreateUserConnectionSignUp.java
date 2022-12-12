package com.yidiandian.support;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author: 云澜
 * @Date: 2022/12/12 10:54
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component
public class AutoCreateUserConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        return connection.getDisplayName();
    }
}
