package com.APIgateway.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String userId;
	
	private String accessToken;
	
	private String refreshToken;
	
	private Long expiredAt;
	
	private List<String> authorities;
	
}
