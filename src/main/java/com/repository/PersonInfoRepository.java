package com.repository;

import com.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonInfoRepository extends JpaRepository<PersonInfo, Long> {

    @Query("select p from PersonInfo p where (current_date - p.dob) / 365 >= 18")
    List<PersonInfo> findAllAdults();

    @Query("select p from PersonInfo p where (current_date - p.dob) / 365 < 18")
    List<PersonInfo> findAllChildren();

    @Query("select p from PersonInfo p where " +
            "p.surname = :surname " +
            "and p.name = :name " +
            "and p.middle_name = :middle_name " +
            "and p.dob = :dob " +
            "and p.phone = :phone " +
            "and p.email = :email " +
            "and p.intl_passport_num = :intl_passport_num " +
            "and p.intl_passport_series = :intl_passport_series ")
    PersonInfo findExisting(@Param("surname") String surname,
                              @Param("name") String name,
                              @Param("middle_name") String middle_name,
                              @Param("dob") LocalDate dob,
                              @Param("phone") String phone,
                              @Param("email") String email,
                              @Param("intl_passport_num") String intl_passport_num,
                              @Param("intl_passport_series") String intl_passport_series);

    @Query("select p from PersonInfo p where " +
            "p.surname = :surname " +
            "and p.name = :name " +
            "and p.middle_name = :middle_name " +
            "and p.dob = :dob " +
            "and p.phone = :phone " +
            "and p.email = :email ")
    PersonInfo findExisting2(@Param("surname") String surname,
                            @Param("name") String name,
                            @Param("middle_name") String middle_name,
                            @Param("dob") LocalDate dob,
                            @Param("phone") String phone,
                            @Param("email") String email);
}
