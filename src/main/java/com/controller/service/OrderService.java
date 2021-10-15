package com.controller.service;

import com.entity.Order;
import com.entity.Tour;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public int count() {
        return (int) orderRepository.count();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Long getTourId(Long orderId) {
        Tour tourId = findById(orderId).getId_tour();
        return tourId.getId();
    }
}
