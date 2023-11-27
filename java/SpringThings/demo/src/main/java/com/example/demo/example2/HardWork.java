package com.example.demo.example2;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy
public class HardWork implements Quotes {
	public HardWork() {
		System.out.println("HardWork()");
	}
	
	@Override
	public List<String> getQuotes(){
		return List.of("if you feel like quiting just remember why you started","how badly you want it");
		
	}
}
