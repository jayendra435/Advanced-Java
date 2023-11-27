package com.example.demo.example2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
@Component
//@SpringBootApplication
public class PrintQuotes implements CommandLineRunner{
//	@Autowired
	Quotes quote1;
//	@Autowired
//	Quotes quote2;
	@Autowired
	public PrintQuotes(Quotes quote1){
		System.out.println("PrintQuotes()");
//		System.out.println(quote1);
		this.quote1=quote1;
	}
	@PostConstruct
	public void postConstruct() {
		System.out.println("PostConstruct");
		System.out.println(quote1);
//		System.out.println(quote2);
	}
	
	@PreDestroy
	public void beforeDestory() {
		System.out.println("PreDestory");
	}
	
	@Override
	public void run(String... args) throws Exception {
		  for(var quote : quote1.getQuotes())
			  System.out.println(quote);
		
	}
//	public static void main(String[] args) {
//		SpringApplication.run(TestQuotes.class,args);
//	}
}
