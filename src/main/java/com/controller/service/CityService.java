package com.controller.service;

import com.entity.City;
import com.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public int count() {
        return (int) cityRepository.count();
    }

    public void createCity(City city) {
        cityRepository.save(city);
    }

    public City findById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Cacheable("citiesOfACountry")
    public List<City> citiesOfCountry(Long id_country) {return cityRepository.citiesOfCountry(id_country);}
}
