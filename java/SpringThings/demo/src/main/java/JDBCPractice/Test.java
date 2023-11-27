package JDBCPractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test implements CommandLineRunner{

	@Autowired
	CategoryManager cm ;
	
	public static void main(String[] args)  {
		 SpringApplication.run(Test.class, args);

	}
	
	public void run(String...args) {
		System.out.println("Welcome bro");
		cm.countRows();
		cm.descFromCode();
		cm.print();
	}
}
