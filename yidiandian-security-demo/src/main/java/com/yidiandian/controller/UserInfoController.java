package com.yidiandian.controller;

import com.yidiandian.entity.UserInfo;
import com.yidiandian.service.UserInfoOperationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/11 12:59
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    UserInfoOperationService userInfoOperationService;

    @PostMapping("/saveUser")
    public String createUser(@Valid @RequestBody UserInfo userInfo, BindingResult bindingResult){
        UserInfo exists = userInfoOperationService.isExists( userInfo.getUserName() );
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach( x->{
                FieldError errorField = (FieldError) x;
                System.out.println(errorField.getField()+":"+ x.getDefaultMessage());
            });
        }
        return "saveUser";
    }

    @PostMapping("/queryByUserName")
    public String queryByUserName(@Valid BindingResult bindingResult, @RequestParam String userName) throws Exception {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach( x->x.getDefaultMessage() );
          }
        return "queryByUserName";
    }

    @PostMapping("/queryUserInfo")
    public String queryUserInfo(@Valid @RequestBody UserInfo userInfo, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach( x->{
                FieldError errorField = (FieldError) x;
                System.out.println(errorField.getField()+":"+ x.getDefaultMessage());
            });
        }
        return "queryUserInfo";
    }


    @GetMapping("/getUserById/{id}")
    public String queryById(@PathVariable Integer id){
      return "queryById";
    }

    @ApiOperation( value = "根据id查询用户信息",notes = "id不能为空",tags = {"用户信息操作"})
    @PostMapping("/updateByName")
    public String updateByName(@RequestParam("userName") String userName){
        if (userInfoOperationService.isExists( userName ) == null){
            //throw new UserInfoNotExistException(userName);
            throw new RuntimeException( "公子，用户不存在哦" );
        }
        return "success";
    }

    @GetMapping("/me")
    public Object getCurrentUser(){
       return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/me2")
    public Object getCurrentUser2(Authentication authentication){
        return authentication;
    }

    @GetMapping("/me3")
    public Object getCurrentUser3(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @GetMapping("/{id:\\d+}")
    public UserInfo getInfo(@ApiParam("用户id") @PathVariable String id) {
//		throw new RuntimeException("user not exist");
        System.out.println("进入getInfo服务");
        UserInfo user = new UserInfo();
        user.setUserName("tom");
        return user;
    }

}
