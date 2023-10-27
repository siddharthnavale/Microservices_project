package com.hotel.service.serviceImpl;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.service.Exception.HotelNotFoundException;
import com.hotel.service.entity.Hotel;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		
		String randomUserId = UUID.randomUUID().toString();
		hotel.setId(randomUserId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String Id) {
		
		return hotelRepository.findById(Id).orElseThrow(() -> new HotelNotFoundException("Hotel Not Found"));
	}

}
