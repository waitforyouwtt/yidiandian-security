package com.yidiandian.service;

import com.yidiandian.entity.UserInfo;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/14 22:34
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface UserInfoService {
    UserInfo findUserInfoByUserName(String userName);
}
