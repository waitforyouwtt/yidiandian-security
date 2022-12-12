package com.yidiandian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/15 21:04
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {
        "org.springframework.security.core.userdetails",
        "org.springframework.security.crypto.password"
})
public class BorwserApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String encode = passwordEncoder.encode( "admin" );
        System.out.println("加密后的密码："+encode);
    }
}
