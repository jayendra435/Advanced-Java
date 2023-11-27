package ProductsManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetweenOrderByProductNameAsc(double minPrice, double maxPrice);
    List<Product> findByProductNameIgnoreCaseContaining(String searchString);

    @Modifying
    @Query("UPDATE Product p SET p.price = p.price * 1.1 WHERE p.id = :productId")
    void increasePriceById(@Param("productId") Long productId);

    List<Product> findByProductNameIsLessThanAndPriceIsGreaterThan(String productName, double minPrice);

    @Query("SELECT p.productName, c.categoryDescription FROM Product p INNER JOIN p.category c")
    List<Object[]> getProductNameAndCategoryDescription();
}
