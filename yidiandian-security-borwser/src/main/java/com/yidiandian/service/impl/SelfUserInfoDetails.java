package com.yidiandian.service.impl;

import com.yidiandian.dao.UserInfoDao;
import com.yidiandian.entity.UserInfo;
import com.yidiandian.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/14 22:05
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component
@Slf4j
public class SelfUserInfoDetails implements UserDetailsService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        UserInfo userInfoByUserName = userInfoDao.findUserInfoByUserName( username );

        //用户名 & 密码 & 用户权限（集合）
       // return new User( username,userInfoByUserName.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList( "admin" ) );
        //上面是最简单的是实现，下面是复杂的校验。下面的4个true ,是根据数据库查询得到的，判断是否可用，账号是否过期，密码是否过期,是否被锁定，
        return new User( username, userInfoByUserName.getPassword() ,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList( "admin" )  );
    }
}
