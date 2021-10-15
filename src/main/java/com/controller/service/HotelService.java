package com.controller.service;

import com.entity.Hotel;
import com.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public int count() {
        return (int) hotelRepository.count();
    }

    public void createHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Cacheable("hotelsForCities")
    public List<Hotel> hotelsForCities(Long id_city){
        return hotelRepository.hotelsForCities(id_city);
    }
}
