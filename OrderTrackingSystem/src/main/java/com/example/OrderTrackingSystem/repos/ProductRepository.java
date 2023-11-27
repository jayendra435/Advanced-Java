package com.example.OrderTrackingSystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OrderTrackingSystem.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
