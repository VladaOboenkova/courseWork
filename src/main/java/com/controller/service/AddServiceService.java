package com.controller.service;

import com.entity.AddService;
import com.repository.AddServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddServiceService {

    @Autowired
    private final AddServiceRepository addServiceRepository;

    public AddServiceService(AddServiceRepository addServiceRepository) {
        this.addServiceRepository = addServiceRepository;
    }

    public int count() {
        return (int) addServiceRepository.count();
    }

    public void createAddService(AddService addService) {
        addServiceRepository.save(addService);
    }

    public AddService findById(Long id) {
        return addServiceRepository.findById(id).orElse(null);
    }

    public List<AddService> findAll() {
        return addServiceRepository.findAll();
    }
}
