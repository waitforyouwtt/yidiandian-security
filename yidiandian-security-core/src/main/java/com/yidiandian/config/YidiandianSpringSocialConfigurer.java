package com.yidiandian.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/4 23:01
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class YidiandianSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public YidiandianSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }


    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess( object );
        filter.setFilterProcessesUrl( filterProcessesUrl );
        return (T) filter;
    }
}