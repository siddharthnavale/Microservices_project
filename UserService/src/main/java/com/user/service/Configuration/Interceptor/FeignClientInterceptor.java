package com.user.service.Configuration.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

	@Autowired
	private OAuth2AuthorizedClientManager oauth2AuthorizedClientManager;
	
	@Override
	public void apply(RequestTemplate template) {
		
		OAuth2AuthorizedClient oAuth2AuthorizedClient = oauth2AuthorizedClientManager
		 .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("UserServiceAsClient").principal("internal").build());
		
		String token=oAuth2AuthorizedClient.getAccessToken().getTokenValue();
		template.header("Authorization", "Bearer "+ token);
		
	}

}
