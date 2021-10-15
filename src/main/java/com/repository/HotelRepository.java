package com.repository;

import com.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select h from Hotel h where h.id_city.id = :id_city")
    List<Hotel> hotelsForCities(@Param("id_city") Long id_city);
}
