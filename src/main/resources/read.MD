redis 启动：
cd C:\tools\redistool
redis-server.exe redis.windows.conf
swagger 访问地址： http://localhost:8080/swagger-ui.html

知识点：
1.自定义注解：validator package,使用见UserInfo.java 中userName
2.自定义错误页面：404，500 ，在resources 下面创建resources/error/404.html & 500.html 页面即可
3.自定义异常：self.exception package 下的代码。
4.restful api 的三种拦截机制：
  
  1.过滤器filter
    过滤器起作用：@Component该注解使过滤器起作用，但是如果使用第三方过滤器，没有该注解，则需要配置，示例：见WebFilterConfig, @Component 等同于WebFilterConfig,选择一种即可。
        局限性：只能够拿到http的请求&响应。当前请求由哪个控制器的哪个method控制的并不知道，如若想知道，则需要用interceptor。 
  2.拦截器 interceptor
    拦截器起作用：@Component //使拦截器器作用，但要配合WebInterceptorConfig,使其继承 extends WebMvcConfigurerAdapter
        注意：@ControllerAdvice 处理在interceptor处理之前，所以userInfoController updateByName 如果抛出UserInfoNotExistException异常，则不打印信息，如果使用RunTimeException才会打印信息
  
  3.切片 aspect
        局限性：只可以拿到参数，拿不到控制器和method
        
5.配置swagger
6.使用spring security 的默认配置，见：application.properties
7.使用表单进行定义spring security 登录页面【表单登录/basic】：见yidiandian-security-brower 项目中 BrowserSecurityConfig   
8.使用数据库的真实数据来验证用户是否可用：见SelfUserInfoDetails
9.使用spring security 的密码加密解密配置：见BrowserSecurityConfig passwordEncoder
    特点：相同的密码，例如123456，但是存入数据库的字符串却不同，因为会加入随机的盐。
10.使用spring security 的密码加密解密的示例：见SelfUserInfoDetails    
11.自定义登录页面：在resources 下创建yidiandian-signIn.html，但还要在BrowserSecurityConfig 中配置yidiandian-signIn.html
   .loginPage( "/yidiandian-signIn.html" )   
   .antMatchers( "yidiandian-signIn.html" ).permitAll()
   如果不配置下面的一句话，则会抛出错误：localhost 将您重定向的次数过多。
   .loginProcessingUrl( "/security-login" )
   此时，可以进行登录，但还需要进行下面的配置，不然会报错：跨域伪造403
   .and()
   .csrf().disable()
12.使上述符合restful风格:注释掉原来的BrowserSecurityConfig
    // .loginPage( "/yidiandian-signIn.html" )
    // .antMatchers( "/yidiandian-signIn.html" ).permitAll()
    新加BrowserSecurityController类，以及在BrowserSecurityConfig中加：
       .loginPage( "/authentication/require" )
       .antMatchers( "/authentication/require" ).permitAll()
       
13.如果用户有登录页，则用用户的登录页，如果用户没有登录页，则用公共标准登录页：见BrowserSecurityController       
   BrowserProperties SecurityCoreConfig  SecurityProperties /demo-signIn.html
   application.properties 中的：yidiandian.security.browser.loginPage = /demo-signIn.html
14.获取登录人信息的三种方式：/me /me2 /me3 [其中me = me2] ,me3 是只返回用户的信息
15.加图形验证码：获取图形验证码控制器ValidateCodeController，yidiandian-signIn.html 页面输入图形验证功能: BrowserSecurityConfig ： "/code/image" 输出图片到登录页面。
   使图形验证码起作用：ValidateCodeFilter  ，BrowserSecurityConfig 配置过滤器，一定在验证用户信息之前，before:http.addFilterBefore( validateCodeFilter, UsernamePasswordAuthenticationFilter.class )
  【图形验证码功能OK】
16.优化图形验证码功能code：ImageCodeProperties ValidateCodeProperties & SecurityProperties引入ValidateCodeProperties配置，
   ValidateCodeController 使图形验证码可根据配置生成长，宽，长度，过期时间等。
   application.properties 可以配置长，宽，会覆盖掉的:覆盖的规则是：请求--》配置---》默认
   请求参数覆盖配置参数，配置参数覆盖默认参数。如果都没有，则是默认参数。
17.配置图形验证码需要过滤的url :BrowserSecurityConfig
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler( yidiandianAuthenctiationFailureHandler );
        validateCodeFilter.setSecurityProperties( securityProperties );
        validateCodeFilter.afterPropertiesSet();   
    ValidateCodeFilter 可配置的选项
    application.properties :#配置哪些路径需要图形验证码的校验
18.图形验证码实现可配置：ValidateCodeGenerator[interface] ,ImageCodeGenerator[impl]【此时没有加@Service,而是用ValidateCodeGenerator代替】
ValidateCodeGenerator ，ConditionalOnMissingBean(name = "codeGenerator"),如果没有name 里面的类，则用默认的，如果有，则用自定义的，自定义
见demo：SelfImageCodeGenerator
19.记住我功能 :页面加支持，name 是固定的    <td colspan='2'><input name="remember-me" type="checkbox" value="true" />记住我</td>
   BrowserProperties 配置过期时间
   BrowserSecurityConfig ：DataSource UserDetailsService persistentTokenRepository
     .rememberMe()
                   .tokenRepository( persistentTokenRepository() )
                   .tokenValiditySeconds( securityProperties.getBrowser().getRememberMeSeconds() )
                   .userDetailsService( userDetailsService )

20.发送短信：yidiandian-signIn.html 支持发送短信验证码功能。 SmsCodeSender DefaultSmsCodeSender SelfSmsCodeGenerator
 ValidateCodeBeanConfig smsCodeSender 手机短信配置[手机发送验证码OK]
 
21. 优化走一波：optimization package 代码
22.支持手机短信：见authentication package ，需要自定义token,filter,provider,校验filter 等，还有在BrowserSecurityConfig 支持配置等。
23.oauth 协议的四种授权模式：
   1.授权码模式：authorization code [功能最完整，认证最严密的模式]
   2.密码模式：resource owner password credentials
   3.简化模式：implicit[少用]
   4.客户端模式：client credentials[少用]
   
 




    
   
   
       
  

