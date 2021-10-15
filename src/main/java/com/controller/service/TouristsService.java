package com.controller.service;

import com.entity.Tourists;
import com.repository.TouristsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristsService {

    @Autowired
    private final TouristsRepository touristsRepository;

    public TouristsService(TouristsRepository touristsRepository) {
        this.touristsRepository = touristsRepository;
    }

    public int count() {
        return (int) touristsRepository.count();
    }

    public Tourists createTourists(Tourists tourists) {
        return touristsRepository.save(tourists);
    }

    public Tourists findById(Long touristsId) {
        return touristsRepository.findById(touristsId).orElse(null);
    }

    public List<Tourists> findAll() {
        return touristsRepository.findAll();
    }

    public  List<Tourists> findAllForOrder(Long id_order){
        return touristsRepository.touristForOrder(id_order);
    }
}
