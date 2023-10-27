package com.rating.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.service.entity.Rating;
import com.rating.service.service.RatingService;



@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {

		Rating addedRating = ratingService.createRating(rating);
		return new ResponseEntity<>(addedRating, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating() {

		List<Rating> allRating = ratingService.getAllRating();
		return new ResponseEntity<>(allRating, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')") 
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId) {

		List<Rating> allRating = ratingService.getAllRatingByUserId(userId);
		return new ResponseEntity<>(allRating, HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{hostelId}")
	public ResponseEntity<List<Rating>> getAllRatingByHostelId(@PathVariable String hostelId) {

		List<Rating> allRating = ratingService.getAllRatingByHostelId(hostelId);
		return new ResponseEntity<>(allRating, HttpStatus.OK);
	}
}
