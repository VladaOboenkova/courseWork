package com.controller.service;

import com.entity.TypeOfRoom;
import com.repository.TypeOfRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfRoomService {

    @Autowired
    private final TypeOfRoomRepository typeOfRoomRepository;

    public TypeOfRoomService(TypeOfRoomRepository typeOfRoomRepository) {
        this.typeOfRoomRepository = typeOfRoomRepository;
    }

    public int count() {
        return (int) typeOfRoomRepository.count();
    }

    public void createTypeOfRoom(TypeOfRoom typeOfRoom) {
        typeOfRoomRepository.save(typeOfRoom);
    }

    public TypeOfRoom findById(Long id) {
        return typeOfRoomRepository.findById(id).orElse(null);
    }

    public List<TypeOfRoom> findAll() {
        return typeOfRoomRepository.findAll();
    }
}
