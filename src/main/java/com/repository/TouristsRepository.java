package com.repository;

import com.entity.Tourists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TouristsRepository extends JpaRepository<Tourists, Long> {

    @Query("select t from Tourists t where t.id_order.id = :id_order")
    List<Tourists> touristForOrder(@Param("id_order") Long id_order);
}
