package com.user.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.UserNotFoundException;
import com.user.service.external.service.HotelService;
import com.user.service.repository.UserRepository;
import com.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Override
	public User saveUser(User user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}
	
//We doing microService communication with help of RestTemplate 
/*	@Override
	public User getUserById(String Id) {

		User user = userRepository.findById(Id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
		Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+Id, Rating[].class);
		
		List<Rating> ratingsList = Arrays.stream(ratings).toList();
		
		List<Rating> ratingWithHotel = ratingsList.stream().map(rating ->{
			ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = response.getBody();
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingWithHotel);
		return user;
	}
*/
	//We doing communication with HotelService with help of Feign Client 
	@Override
	public User getUserById(String Id) {

		User user = userRepository.findById(Id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
		Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+Id, Rating[].class);
		
		List<Rating> ratingsList = Arrays.stream(ratings).toList();
		
		List<Rating> ratingWithHotel = ratingsList.stream().map(rating ->{
			//ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel = response.getBody();
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingWithHotel);
		return user;
	}
}
