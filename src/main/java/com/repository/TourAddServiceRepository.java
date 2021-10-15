package com.repository;

import com.entity.Tour;
import com.entity.TourAddService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourAddServiceRepository extends JpaRepository<TourAddService, Long> {

    @Query("select tas from TourAddService tas where tas.id_tour = :id_tour")
    List<TourAddService> addServiceForTour(@Param("id_tour") Tour id_tour);
}
