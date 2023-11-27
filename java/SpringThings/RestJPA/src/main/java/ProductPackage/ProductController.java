package ProductPackage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/jay")
//@Controller
public class ProductController {
	
	@Autowired
	ProductRepo pr;
	
	@GetMapping("/prod")
	public List<Product> displayProducts(){
		return pr.findAll(); 
	}
	@PutMapping("/prod/{id}")
	public ResponseEntity<String> updateProductQuantity(@PathVariable int id, @RequestBody int newQuantity) {
	    Optional<Product> optionalProduct = pr.findById(id);

	    if (optionalProduct.isPresent()) {
	        Product product = optionalProduct.get();
	        product.setQuantity(newQuantity);
	        pr.save(product);
	        return ResponseEntity.ok("Product quantity updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " not found");
	    }
	}
	
	@GetMapping("/prod/category/{category}")
	public List<Product> getProductsByCategory(@PathVariable String category) {
	    return pr.findByCategory(category);
	}
	
	@DeleteMapping("/prod/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable int id) {
	    Optional<Product> optionalProduct = pr.findById(id);

	    if (optionalProduct.isPresent()) {
	        pr.deleteById(id);
	        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
	    } else {
	        return ResponseEntity.ok("Product with ID " + id + " not found");
	    }
	}
	
	@DeleteMapping("/prod/name/{name}")
	public ResponseEntity<String> deleteProductsByName(@PathVariable String name) {
	    List<Product> products = pr.findByName(name);

	    if (!products.isEmpty()) {
	        pr.deleteAll(products);
	        return ResponseEntity.ok("Products with name " + name + " deleted successfully");
	    } else {
	        return ResponseEntity.ok("No products found with name " + name);
	    }
	}
	
	@GetMapping("prod/top5")
	public List<Product> getTop5ProductsByPrice() {
	    List<Product> top5Products = pr.findTop5ByOrderByPriceDesc();
	    return top5Products;
	}
	
	@GetMapping("prod/price-range")
	public List<Product> getProductsInPriceRange(
	    @RequestParam("minPrice") double minPrice,
	    @RequestParam("maxPrice") double maxPrice
	) {
	    List<Product> productsInPriceRange = pr.findByPriceBetween(minPrice, maxPrice);
	    return productsInPriceRange;
	}
	
	@PostMapping("prod/add")
	public ResponseEntity<String> addProduct(@RequestBody Product newProduct) {
	    pr.save(newProduct);
	    return ResponseEntity.ok("Product added successfully");
	}
	


}
