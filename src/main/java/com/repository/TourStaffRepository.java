package com.repository;

import com.entity.TourStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourStaffRepository extends JpaRepository<TourStaff, Long> {

    @Query("select ts from TourStaff ts where ts.id_position = 6 or ts.id_position = 7")
    List<TourStaff> getManagers();

}
