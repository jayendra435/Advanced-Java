package com.example.demo.example3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test implements CommandLineRunner {
			@Autowired
			public Payment pay;
			@Override
			public void run(String... args) throws Exception {
				pay.getClass();
				pay.payment();
				pay.validateCC();
				pay.process();
			}

			public static void main(String[] args) {
				SpringApplication.run(Test.class, args);
			}

		}	


