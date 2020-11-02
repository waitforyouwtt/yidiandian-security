package com.yidiandian.social.qq;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import java.io.IOException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/31 12:58
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Slf4j
//这个是多实例的类： 不能用@Service &@Component，否则会有线程安全问题
public class QqServiceImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID= "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    private String appId;
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper(  );

    public QqServiceImpl(String accessToken, String appId) {
        super( accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER );
        this.appId = appId;
        String url = String.format( URL_GET_OPENID,accessToken );
        String result = getRestTemplate().getForObject( url,String.class );
        log.info( "调用QQ返回的openId result 是：{}",result );
        this.openId = StringUtils.substringBetween(result,"\"openid\":","}");
        log.info( "qq openid 赋值的value是：{}",openId );
    }

    @Override
    public QqUserInfo getQqUserInfo() {
        String url = String.format( URL_GET_USERINFO,appId,openId );
        String result = getRestTemplate().getForObject( url,String.class );
        log.info("调用获取qq用户信息返回的数据是：{}", JSONUtil.parseObj(result));
        QqUserInfo entity = JSON.toJavaObject( (JSON) JSON.toJSON( result ), QqUserInfo.class );
        QqUserInfo info = null;
        try {
             info = objectMapper.readValue(  result,QqUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        info.setOpenId( openId );
        return info;
    }
}
