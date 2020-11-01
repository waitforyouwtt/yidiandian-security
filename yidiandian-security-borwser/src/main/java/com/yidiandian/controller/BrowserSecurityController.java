package com.yidiandian.controller;

import com.yidiandian.properties.SecurityProperties;
import com.yidiandian.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/17 22:11
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    /**
     * 判断引发跳转的是html，还是一个控制器请求
     */
    private RequestCache requestCache = new HttpSessionRequestCache();
    //借助它进行跳转页面
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份验证时，跳转的控制器
     */
    @GetMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest( request,response );
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引起跳转的请求是：{}",targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
                redirectStrategy.sendRedirect( request,response,securityProperties.getBrowser().getLoginPage() );
            }
        }
        return new SimpleResponse("访问的请求url 需要进行身份认证，请前往登录页");
    }
}
