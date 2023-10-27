package com.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entity.User;
import com.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// private int retryCount=1;
	@Autowired
	private UserService UserService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User addedUser = UserService.saveUser(user);
		return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {

		List<User> allUser = UserService.getAllUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);
	}
	/*
    @GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {

		User user = UserService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
*/

		//Here we implemented Circuit breaker
    @GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {

		User user = UserService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/*
	 * Here we implemented Retry
	 * @GetMapping("/{userId}")
	@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		User user = UserService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}*/
	

	
	public ResponseEntity<User> ratingHotelFallback (String userId, Exception ex){
		User user=User.builder()
				.email("dummy@gmil.com")
				.name("Dummy")
				.about("This id Dummy user created beacuse some service down")
				.id("dummy")
				.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}

	
}
