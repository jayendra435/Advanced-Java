package com.example.demo.example3;

import org.springframework.stereotype.Component;

@Component
public class Payment {
	public void payment() {
        System.out.println("Payment processing...");
    }

    public void validateCC() {
        System.out.println("Credit card validation...");
    }

    public void process() {
        System.out.println("Processing payment...");
    }
}
