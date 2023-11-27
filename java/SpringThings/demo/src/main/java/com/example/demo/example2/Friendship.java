package com.example.demo.example2;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary
public class Friendship implements Quotes {
	public Friendship() {
		System.out.println("Friendship()");
	}
	@Override
	public List<String> getQuotes(){
		return List.of("A friend in need is a friend indeed","Choose your friends wisely");
		
	}
}
