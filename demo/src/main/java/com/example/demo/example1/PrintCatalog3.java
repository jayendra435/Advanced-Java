package com.example.demo.example1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class PrintCatalog3 implements CommandLineRunner {
	Catalog catalog;
	

	@Autowired(required = false)
	public void setCatalog(Catalog catalog) {
		System.out.println("setCatalog()");
		this.catalog = catalog;
	}

   
	public PrintCatalog3() {
		 System.out.println("PrintCatalog3()");
	}



	@Override
	public void run(String... args) throws Exception {
		if (catalog == null)
			return;
		
		for (var book : catalog.getBooks())
			System.out.println(book);

	}

}