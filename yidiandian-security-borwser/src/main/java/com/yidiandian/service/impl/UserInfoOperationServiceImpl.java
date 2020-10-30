package com.yidiandian.service.impl;

import com.yidiandian.dao.UserInfoDao;
import com.yidiandian.entity.UserInfo;
import com.yidiandian.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/14 22:35
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class UserInfoOperationServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public UserInfo findUserInfoByUserName(String userName){
        return userInfoDao.findUserInfoByUserName(userName);
    }
}
