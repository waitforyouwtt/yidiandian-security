package com.yidiandian.validate;


import com.yidiandian.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/23 21:52
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
//OncePerRequestFilter 保证过滤器只会执行一次
//初始化bean
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private SecurityProperties securityProperties;
    private AntPathMatcher pathMatcher = new AntPathMatcher(  );

    private Set<String> urls = new HashSet<>(  );

    @Override
    public void afterPropertiesSet () throws ServletException {
        urls.add( "/security-login" );
        super.afterPropertiesSet();
        System.out.println("得到的值："+securityProperties);
         String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),","  );
        if (configUrls == null){
            return;
        }
        for (String configUrl : configUrls){
            urls.add(configUrl);
        }
    }

    /**
     * 可配置要验证码的过滤器
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (pathMatcher.match( url, request.getRequestURI() )) {
                action = true;
            }
        }
        if (action) {
            try {
                validate( new ServletWebRequest( request ) );
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure( request, response, e );
                return;
            }
        }

        filterChain.doFilter( request, response );
    }

    /**
     * 不支持配置url 代码
     * @param request
     * @throws ServletRequestBindingException
     */
   /* @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals( "/security-login", request.getRequestURI() )
                && StringUtils.equalsIgnoreCase( request.getMethod(), "post" )) {
            try {
                validate( new ServletWebRequest( request ) );
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure( request, response, e );
                return;
            }
        }
        filterChain.doFilter( request, response );
    }*/
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
      ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute( request,ValidateCodeController.SESSION_KEY+"_IMAGE" );
      String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");
      if (StringUtils.isBlank( codeInRequest )){
          throw new ValidateCodeException( "验证码的值不能为空" );
      }
        if (codeInSession == null){
            throw new ValidateCodeException( "验证码不存在" );
        }
        if (codeInSession.isExpried()){
            sessionStrategy.removeAttribute( request,ValidateCodeController.SESSION_KEY );
            throw new ValidateCodeException( "验证码已过期" );
        }
        if (!StringUtils.equals(codeInSession.getCode() ,codeInRequest )){
            throw new ValidateCodeException( "验证码不匹配" );
        }
        sessionStrategy.removeAttribute(  request,ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
