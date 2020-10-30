package com.yidiandian.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/12 22:23
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Configuration
public class WebFilterConfig {
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter( timeFilter );
        //可以设置具体在哪些资源路径上起作用
        List<String> urls = new ArrayList<>(  );
        urls.add( "/*" );
        registrationBean.setUrlPatterns( urls );
        return registrationBean;
    }
}
