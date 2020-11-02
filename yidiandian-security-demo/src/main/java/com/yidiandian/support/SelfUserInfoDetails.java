package com.yidiandian.support;

import com.yidiandian.dao.UserInfoDao;
import com.yidiandian.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/14 22:05
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component("selfUserInfoDetails")
@Slf4j
public class SelfUserInfoDetails implements UserDetailsService , SocialUserDetailsService {

    @Resource
    UserInfoDao userInfoDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info( "普通表单登录" );
        return queryUserDetailsFromDatabase( username );
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info( "社交用户登录" );
        return queryUserDetailsFromDatabase( userId );
    }

    private SocialUserDetails queryUserDetailsFromDatabase(String username){
        //根据用户名查询用户信息
        UserInfo userInfoByUserName = userInfoDao.queryUserInfoByUserName( username );
        //用户名 & 密码 & 用户权限（集合）
        // return new User( username,userInfoByUserName.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList( "admin" ) );
        //上面是最简单的是实现，下面是复杂的校验。下面的4个true ,是根据数据库查询得到的，判断是否可用，账号是否过期，密码是否过期,是否被锁定，
        return new SocialUser( username, userInfoByUserName.getPassword() ,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList( "admin" )  );
    }
}
