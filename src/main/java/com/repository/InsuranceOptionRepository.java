package com.repository;

import com.entity.Insurance;
import com.entity.InsuranceOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceOptionRepository extends JpaRepository<InsuranceOption, Long> {

    @Query("select inop from InsuranceOption inop where inop.id_insurance = :id_insurance")
    List<InsuranceOption> optionsForInsurance(@Param("id_insurance") Insurance id_insurance);

}
