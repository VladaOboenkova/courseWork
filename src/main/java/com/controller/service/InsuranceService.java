package com.controller.service;

import com.entity.Insurance;
import com.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private final InsuranceRepository insuranceRepository;

    public InsuranceService (InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public int count() {
        return (int) insuranceRepository.count();
    }

    public void createInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
    }

    public Insurance findById(Long id) {
        return insuranceRepository.findById(id).orElse(null);
    }

    @Cacheable("allInsurances")
    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }
}
