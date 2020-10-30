package com.yidiandian.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/12 23:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component //使拦截器器作用，但要配合WebInterceptorConfig,使其继承 extends WebMvcConfigurerAdapter
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 在访问控制器的method方法之前会被调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info( "timeInterceptor preHandle start......" );
        log.info("当前执行的控制器&方法是：{}" ,((HandlerMethod)o).getBean().getClass().getName()+":"+((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute( "startTime",System.currentTimeMillis());
        return true;
    }

    /**
     * 控制器的方法处理之后该方法被调用，如果控制器的方法抛出异常，该方法则不会被调用了
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info( "timeInterceptor postHandle 耗时：{}",System.currentTimeMillis() - startTime );
    }

    /**
     * 控制器的方法是否正常执行完成，该方法都会被调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info( "timeInterceptor afterCompletion 耗时：{}",System.currentTimeMillis() - startTime );
        log.info( "正常执行完成，e不会有信息，如果抛出异常，则e会有信息:{}",e );
    }
}
