package com.yidiandian.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/12 22:07
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Slf4j
@Component //使过滤器起作用,
//@Component 使用第三方过滤器，假如没有该注解，则需要配置，示例：见WebFilterConfig, @Component 等同于WebFilterConfig,选择一种即可。
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       log.info( "timeFilter init......" );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      log.info( "filter start......" );
      long startTime = System.currentTimeMillis();
      filterChain.doFilter( servletRequest,servletResponse );
      log.info( "filter finished......耗时：{}",System.currentTimeMillis()-startTime );
    }

    @Override
    public void destroy() {
        log.info( "timeFilter destroy......" );
    }
}
