package com.yidiandian.social.qq;

import lombok.Data;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/31 13:00
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class QqUserInfo {

    /**
     * 	返回码
     */
    private String ret;
    /**
     * 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
     */
    private String msg;
    /**
     *
     */
    private String openId;
    /**
     * 不知道什么东西，文档上没写，但是实际api返回里有。
     */
    private String is_lost;
    /**
     * 省(直辖市)
     */
    private String province;
    /**
     * 市(直辖市区)
     */
    private String city;
    /**
     * 出生年月
     */
    private String year;
    /**
     * 用户昵称
     */
   private String nickname;
    /**
     * 大小为30×30像素的QQ空间头像URL。
     */
   private String figureurl;
    /**
     * 大小为50×50像素的QQ空间头像URL。
     */
   private String figureurl_1;
    /**
     * 大小为100×100像素的QQ空间头像URL。
     */
   private String figureurl_2;
    /**
     * 大小为40×40像素的QQ头像URL。
     */
   private String figureurl_qq_1;
    /**
     * 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
     */
   private String figureurl_qq_2;
    /**
     * 性别。 如果获取不到则默认返回"男"
     */
   private String gender;
    /**
     * 用户是否是黄钻用户：0 不是 1 是
     */
   private int is_yellow_vip;
    /**
     * 用户是否是黄钻用户：0 不是 1 是
     */
   private int vip;
   private int yellow_vip_level;
   private int level;
   private int is_yellow_year_vip;
}
