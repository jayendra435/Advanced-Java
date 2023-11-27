package com.example.OrderTrackingSystem.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OrderTrackingSystem.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {

	
}
