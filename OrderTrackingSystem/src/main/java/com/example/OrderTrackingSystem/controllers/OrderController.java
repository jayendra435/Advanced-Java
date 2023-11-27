package com.example.OrderTrackingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderTrackingSystem.repos.OrderRepository;

@RestController
public class OrderController {
	@Autowired
	OrderRepository orderRepository;
	
}
