package ProductPackage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

	List<Product> findByCategory(String category);
	List<Product> findByName(String name);
	List<Product> findTop5ByOrderByPriceDesc();
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
