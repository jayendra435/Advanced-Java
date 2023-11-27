package ProductsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class Test implements CommandLineRunner{

	public static void main(String[] args)  {
		 SpringApplication.run(Test.class, args);
	}
	
	@Autowired
	 private ProductRepository productRepository;

	    @Override
	    public void run(String... args) throws Exception {
	 
	         productRepository.findByPriceBetweenOrderByProductNameAsc(100, 200);
	    }


}
