package com.controller.service;

import com.entity.TourStaff;
import com.repository.TourStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourStaffService {

    @Autowired
    private final TourStaffRepository tourStaffRepository;

    public TourStaffService(TourStaffRepository tourStaffRepository) {
        this.tourStaffRepository = tourStaffRepository;
    }

    public int count() {
        return (int) tourStaffRepository.count();
    }

    public void createTourStaff(TourStaff tourStaff) {
        tourStaffRepository.save(tourStaff);
    }

    public TourStaff findById(Long id) {
        return tourStaffRepository.findById(id).orElse(null);
    }

    public List<TourStaff> findAll() {
        return tourStaffRepository.findAll();
    }

    @Cacheable("allMangers")
    public List<TourStaff> findAllManagers(){
//        List<TourStaff> tourStaff = findAll();
//        List<TourStaff> managers = new ArrayList<>();
//        for (TourStaff ts : tourStaff) {
//            if (ts.getId_position().getPosition_name().equals("Менеджер") ||
//                    ts.getId_position().getPosition_name().equals("Старший менеджер")){
//                managers.add(ts);
//            }
//        }
//        return managers;
        return tourStaffRepository.getManagers();
    }
}

