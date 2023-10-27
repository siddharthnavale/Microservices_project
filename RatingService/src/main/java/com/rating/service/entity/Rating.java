package com.rating.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Ratings")
public class Rating {
	
	@Id
	private String id;
	
	private String userId;
	
	private String hotelId;
	
	private int rating;
	
	private String remark;
	
	
	

}
