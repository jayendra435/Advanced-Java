package springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class Test implements CommandLineRunner {
	@Autowired
	CategoryManager cats;

	@Autowired
	SJIDemo sjiDemo;

	public static void main(String[] args) {
		SpringApplication.run(Test.class, args);
	}

	public void run(String... args) {
		 cats.showCategoryCount();

		 cats.showCategoryCount();
		 cats.listCategoryCodes();
		 cats.updateCatDescWithNames("c3", "Category 3");
		 sjiDemo.add("c5","Category 5");

//		 cats.sale(100,2);

//		try {
//			System.out.println("Swapping Descriptions!");
//			cats.swapDescriptions("c5", "c30");
//			System.out.println("Operation Completed!");
//		} catch (Exception ex) {
//			System.out.println("Operation Failed");
//		}

	}

}
