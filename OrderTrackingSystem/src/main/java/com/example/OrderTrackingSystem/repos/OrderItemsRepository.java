package com.example.OrderTrackingSystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OrderTrackingSystem.entities.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer> {

}
