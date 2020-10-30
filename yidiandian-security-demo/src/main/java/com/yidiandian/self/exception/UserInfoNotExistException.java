package com.yidiandian.self.exception;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/12 21:41
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class UserInfoNotExistException extends RuntimeException {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInfoNotExistException(String userId){
        super("user not exist");
        this.userId = userId;
    }
}
