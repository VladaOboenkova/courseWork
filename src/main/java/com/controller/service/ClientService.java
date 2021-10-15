package com.controller.service;

import com.entity.Client;
import com.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public int count() {
        return (int) clientRepository.count();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public List<Client> findAllSuitable(LocalDateTime date){
        List<Client> clients = findAll();
        List<Client> suitableClients = new ArrayList<>();
        for (Client c : clients){
            if (c.getId_person_info().getDob().plusYears(18).toEpochDay() <=
                    date.toLocalDate().minusDays(1).toEpochDay()){
                suitableClients.add(c);
            }
        }
        return  suitableClients;
    }

    public Client findExistingClient(String passport_num, String passport_series) {
        return clientRepository.findExistingClient(passport_num, passport_series);
    }

}