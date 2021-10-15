package com.repository;

import com.entity.PersonInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonInsuranceRepository extends JpaRepository<PersonInsurance, Long> {

    @Query("select pi from PersonInsurance pi where pi.policy_num = :policy_num")
    PersonInsurance findPolicyNum(@Param("policy_num") String policy_num);
}
