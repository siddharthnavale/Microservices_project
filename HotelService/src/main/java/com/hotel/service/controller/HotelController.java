package com.hotel.service.controller;

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

import com.hotel.service.entity.Hotel;
import com.hotel.service.service.HotelService;


@RestController
@RequestMapping("/hotel")
public class HotelController {


	@Autowired
	private HotelService hotelService;

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {

		Hotel addedhotel = hotelService.saveHotel(hotel);
		return new ResponseEntity<>(addedhotel, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')") 
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel() {

		List<Hotel> allHotel = hotelService.getAllHotel();
		return new ResponseEntity<>(allHotel, HttpStatus.OK);
	}
 
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {

		Hotel hotel = hotelService.getHotelById(hotelId);
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}


}
