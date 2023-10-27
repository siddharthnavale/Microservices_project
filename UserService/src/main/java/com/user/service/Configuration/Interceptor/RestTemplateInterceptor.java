package com.user.service.Configuration.Interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	
	private OAuth2AuthorizedClientManager oauth2AuthorizedClientManager;
	
	
	public RestTemplateInterceptor(OAuth2AuthorizedClientManager oauth2AuthorizedClientManager) {
		super();
		this.oauth2AuthorizedClientManager = oauth2AuthorizedClientManager;
	}


	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		OAuth2AuthorizedClient oAuth2AuthorizedClient = oauth2AuthorizedClientManager
				 .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("UserServiceAsClient").principal("internal").build());
		String token=oAuth2AuthorizedClient.getAccessToken().getTokenValue();
		
		request.getHeaders().add("Authorization","Bearer "+ token);
		return execution.execute(request, body);
	}

}
