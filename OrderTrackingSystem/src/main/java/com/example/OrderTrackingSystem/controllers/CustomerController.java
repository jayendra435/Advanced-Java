package com.example.OrderTrackingSystem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.OrderTrackingSystem.entities.Customer;
import com.example.OrderTrackingSystem.entities.Order;
import com.example.OrderTrackingSystem.repos.CustomerRepository;
import com.example.OrderTrackingSystem.repos.OrderItemsRepository;
import com.example.OrderTrackingSystem.repos.OrderRepository;
import com.example.OrderTrackingSystem.repos.ProductRepository;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/getCustomers")
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	

}
