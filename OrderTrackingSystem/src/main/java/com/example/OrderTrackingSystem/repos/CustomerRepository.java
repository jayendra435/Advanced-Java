package com.example.OrderTrackingSystem.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OrderTrackingSystem.entities.Customer;
import com.example.OrderTrackingSystem.entities.Order;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


	
	
}
