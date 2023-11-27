package com.example.demo.example1;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//@Component  // default name is pythonCatalog 
//@Lazy
public class PythonCatalog implements Catalog{
  	
	public PythonCatalog() {
	    System.out.println("PythonCatalog()");
	}

	@Override
	public List<String> getBooks() {
	    return List.of("Python in Nutshell", "Thinking of Python");
	}

}
