package com.example.demo.example1;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class PrintCatalog2 implements CommandLineRunner {

	Catalog catalog;
	
	public PrintCatalog2(Catalog catalog) {
        System.out.println("PrintCatalog2()");
		this.catalog = catalog;
	}

	@Override
	public void run(String... args) throws Exception {
		for (var book : catalog.getBooks())
			System.out.println(book);

	}

}
