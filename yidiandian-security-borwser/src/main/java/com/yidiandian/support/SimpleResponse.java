package com.yidiandian.support;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/17 22:44
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
