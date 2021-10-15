package com.controller.service;

import com.entity.InsuranceType;
import com.repository.InsuranceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceTypeService {

    @Autowired
    private final InsuranceTypeRepository insuranceTypeRepository;

    public InsuranceTypeService(InsuranceTypeRepository insuranceTypeRepository) {
        this.insuranceTypeRepository = insuranceTypeRepository;
    }

    public int count() {
        return (int) insuranceTypeRepository.count();
    }

    public void createInsuranceType(InsuranceType insuranceType) {
        insuranceTypeRepository.save(insuranceType);
    }

    public InsuranceType findById(Long id) {
        return insuranceTypeRepository.findById(id).orElse(null);
    }

    public List<InsuranceType> findAll() {
        return insuranceTypeRepository.findAll();
    }
}
