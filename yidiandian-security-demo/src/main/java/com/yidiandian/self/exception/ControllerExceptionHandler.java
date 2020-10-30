package com.yidiandian.self.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/12 21:57
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserInfoNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> handlerUserInfoNotExistException(UserInfoNotExistException ex){
        Map<String,String> map = new HashMap<>(  16);
        map.put( "userId",ex.getUserId() );
        map.put( "message",ex.getMessage() );
        return map;
    }
}
