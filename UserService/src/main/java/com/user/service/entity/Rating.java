package com.user.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
	
	private String Id;
	
	private String userId;
	
	private String hotelId;
	
	private int rating;
	
	private String remark;
	
	private Hotel hotel;
	
	
	

}
