package com.yidiandian.social.qq;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/31 17:53
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class QQAdapter implements ApiAdapter<QQ> {
    /**
     * 测试当前api是否是可用的
     * @param qq
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    /**
     * qq api 的数据和connect 数据做适配
     * @param qq
     * @param connectionValues
     */
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
      QqUserInfo user = qq.getQqUserInfo();
      //qq昵称
      connectionValues.setDisplayName( user.getNickname() );
      //qq头像
      connectionValues.setImageUrl( user.getFigureurl_qq_1() );
      //个人主页
      connectionValues.setProfileUrl( null );
      //服务商的openid
      connectionValues.setProviderUserId( user.getOpenId() );
    }

    /**
     * 等同与上面，不过这个是qq 服务返回的标准profile
     * @param qq
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /**
     * 在一些特定的社交网上上才有：微博
     * @param qq
     * @param s
     */
    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
