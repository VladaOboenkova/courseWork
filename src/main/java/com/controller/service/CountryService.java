package com.controller.service;

import com.entity.Country;
import com.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public int count() {
        return (int) countryRepository.count();
    }

    public void createCountry(Country country) {
        countryRepository.save(country);
    }

    public Country findById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Cacheable("allCountries")
    public List<Country> findAll() {
        //System.out.println("we are in");
        return countryRepository.findAll();
    }
}