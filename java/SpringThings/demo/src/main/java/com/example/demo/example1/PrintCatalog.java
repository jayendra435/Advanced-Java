package com.example.demo.example1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component 
public class PrintCatalog implements CommandLineRunner {
	
	@Autowired
	//@Qualifier("pythonCatalog")
	Catalog catalog;
	
	@Autowired
 	Catalog catalog2;
	
	public PrintCatalog() {
	    System.out.println("PrintCatalog()");
	    System.out.println(catalog);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("PostConstruct");
		System.out.println(catalog);
		System.out.println(catalog2);
	}
	
	@PreDestroy
	public void beforeDestory() {
		System.out.println("PreDestory");
	}

	@Override
	public void run(String... args) throws Exception {
		  for(var book : catalog.getBooks())
			  System.out.println(book);
		
	}

}