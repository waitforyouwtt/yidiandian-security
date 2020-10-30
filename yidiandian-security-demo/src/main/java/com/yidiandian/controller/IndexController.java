package com.yidiandian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/11 12:59
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
