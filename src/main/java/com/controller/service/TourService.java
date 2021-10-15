package com.controller.service;

import com.entity.Tour;
import com.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public int count() {
        return (int) tourRepository.count();
    }

    public void createTour(Tour tour) {
        tourRepository.save(tour);
    }

    @Cacheable("tours")
    public Tour findById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Cacheable("toursForHotels")
    public List<Tour> toursForHotels(Long id_hotel,
                              Integer num_of_adults,
                              Integer num_of_children){
        return tourRepository.toursForHotels(id_hotel, num_of_adults, num_of_children);
    }
}
