package com.yidiandian.service.impl;

import com.yidiandian.dao.UserInfoOperationDao;
import com.yidiandian.entity.UserInfoOperation;
import com.yidiandian.service.UserInfoOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/11 20:53
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class UserInfoOperationServiceImpl implements UserInfoOperationService {

    @Autowired
    UserInfoOperationDao userInfoOperationDao;

    @Override
    public UserInfoOperation isExists(String userName) {
        return userInfoOperationDao.findUserInfoByUserName(userName);
    }
}
