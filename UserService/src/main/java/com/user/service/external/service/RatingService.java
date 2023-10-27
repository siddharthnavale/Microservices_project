package com.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.service.entity.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/rating")
	Rating createRating(Rating rating);
	
	@PutMapping("/rating/{ratingId}")
	Rating updateRating(@PathVariable String ratingId,Rating rating);
	
	@DeleteMapping("/rating/{ratingId}")
	Rating deleteRating(@PathVariable String ratingId);
	
}
