package com.controller.service;

import com.entity.PersonInfo;
import com.repository.PersonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonInfoService {

    @Autowired
    private final PersonInfoRepository personInfoRepository;

    public PersonInfoService(PersonInfoRepository personInfoRepository) {
        this.personInfoRepository = personInfoRepository;
    }

    public int count() {
        return (int) personInfoRepository.count();
    }

    public PersonInfo createPersonInfo(PersonInfo personInfo) {
        return personInfoRepository.save(personInfo);
    }

    public PersonInfo findById(Long id) {
        return personInfoRepository.findById(id).orElse(null);
    }

    public List<PersonInfo> findAll() {
        return personInfoRepository.findAll();
    }

    public List<PersonInfo> findAllAdults() {return personInfoRepository.findAllAdults();}

    public List<PersonInfo> findAllChildren() {return personInfoRepository.findAllChildren();}

    public List<PersonInfo> findAllWithIntlPassport(Boolean adult){
        if (adult){
            List<PersonInfo> allAdults = findAllAdults();
            List<PersonInfo> allAdultsWithIntlPassport = new ArrayList<>();
            for (PersonInfo p : allAdults) {
                if (p.getIntl_passport_num() != null && p.getIntl_passport_series() != null) {
                    allAdultsWithIntlPassport.add(p);
                }
            }
            return allAdultsWithIntlPassport;
        } else {
            List<PersonInfo> allChildren = findAllChildren();
            List<PersonInfo> allChildrenWithIntlPassport = new ArrayList<>();
            for (PersonInfo p : allChildren) {
                if (p.getIntl_passport_num() != null && p.getIntl_passport_series() != null) {
                    allChildrenWithIntlPassport.add(p);
                }
            }
            return allChildrenWithIntlPassport;
        }
    }

    public PersonInfo findExisting(String surname,
                                         String name,
                                         String middle_name,
                                         LocalDate dob,
                                         String phone,
                                         String email,
                                         String intl_passport_num,
                                         String intl_passport_series){
        return personInfoRepository.findExisting(surname, name, middle_name, dob, phone, email,
                intl_passport_num, intl_passport_series);
    }

    public PersonInfo findExisting2(String surname,
                                    String name,
                                    String middle_name,
                                    LocalDate dob,
                                    String phone,
                                    String email){
        return personInfoRepository.findExisting2(surname, name, middle_name, dob, phone, email);
    }
}
