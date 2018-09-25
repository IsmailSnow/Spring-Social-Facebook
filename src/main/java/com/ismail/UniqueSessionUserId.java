package com.ismail;

import org.springframework.context.annotation.Configuration;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Configuration
public class UniqueSessionUserId extends SocialConfigurerAdapter {

	@Override
	public UserIdSource getUserIdSource() {
		return new UserIdSource() {

			@Override
			public String getUserId() {
				RequestAttributes request = RequestContextHolder.currentRequestAttributes();
				String uuid = (String) request.getAttribute("_socialUserUUID", RequestAttributes.SCOPE_SESSION);
				if (null == uuid) {
					request.setAttribute("_socialUserUUID", uuid, RequestAttributes.SCOPE_SESSION);
				}
				return uuid;
			}
		};
	}
}
