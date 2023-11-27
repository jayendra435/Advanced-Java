package ProductPackage;

import jakarta.persistence.Column;

//import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer prod_id;
	private String name;
	private String category;
	private int quantity;
	private double price;
	@Override
	public String toString() {
		return "Product [prod_id=" + prod_id + ", name=" + name + ", category=" + category + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getId() {
		return prod_id;
	}
	public void setId(Integer id) {
		this.prod_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
