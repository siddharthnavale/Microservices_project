package com.hotel.service.service;

import java.util.List;

import com.hotel.service.entity.Hotel;

public interface HotelService {

	Hotel saveHotel(Hotel hotel);

	List<Hotel> getAllHotel();

	Hotel getHotelById(String Id);
}
