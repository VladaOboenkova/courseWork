package com.repository;

import com.entity.AddService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddServiceRepository extends JpaRepository<AddService, Long> {
}
