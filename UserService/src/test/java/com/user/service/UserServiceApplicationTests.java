package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	/*
	@Autowired
	private RatingService ratingService;

	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(5).hotelId("").userId("").remark("created using Feign client").build();
		Rating createRating = ratingService.createRating(rating);
		System.out.println("New rating created");
	}*/
}
