package com.repository;

import com.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query("select t from Tour t where " +
            "t.id_hotel.id = :id_hotel " +
            "and t.num_of_adults = :num_of_adults " +
            "and t.num_of_children = :num_of_children")
    List<Tour> toursForHotels(@Param("id_hotel") Long id_hotel,
                              @Param("num_of_adults") Integer num_of_adults,
                              @Param("num_of_children") Integer num_of_children);
}
