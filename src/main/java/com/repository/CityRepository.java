package com.repository;

import com.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.id_country.id = :id_country")
    List<City> citiesOfCountry(@Param("id_country") Long id_country);
}
