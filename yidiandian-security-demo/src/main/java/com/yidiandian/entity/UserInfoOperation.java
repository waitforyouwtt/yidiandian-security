package com.yidiandian.entity;

import com.yidiandian.validator.SelfConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/11 14:55
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class UserInfoOperation implements Serializable {
    private static final long serialVersionUID = -63802581526404914L;

    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户姓名
     */
    //@SelfConstraint(message = "该名字已注册，请重新输入")
    @NotBlank(message = "名字不能为空")
    private String userName;
    /**
     * 用户昵称
     */
    private String userNick;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户性别：0 男 1 女 2 保密
     */
    private Integer gender;
    /**
     * 用户头像
     */
    private String headImage;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户状态：0 正常 1 禁用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;
}
