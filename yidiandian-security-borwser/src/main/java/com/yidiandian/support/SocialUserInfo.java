/**
 * 
 */
package com.yidiandian.support;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhailiang
 *
 */
@Data
public class SocialUserInfo implements Serializable {
	
	private String providerId;
	
	private String providerUserId;
	
	private String nickname;
	
	private String headimg;
}
