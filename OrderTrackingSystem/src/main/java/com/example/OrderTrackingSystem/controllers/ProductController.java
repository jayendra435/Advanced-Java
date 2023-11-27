package com.example.OrderTrackingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderTrackingSystem.repos.ProductRepository;

@RestController
public class ProductController {
	@Autowired
	ProductRepository productRepository;

}
