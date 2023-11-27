package project.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Product")
public class Product {
	@Id
	@Column(name = "Product_Id")
	@Pattern(regexp = "^[A-Z]\\d{3}$", message = "Product ID must consist of an uppercase letter followed by three digits")
	private String productId;

	@Column(name = "Product_Name")
	@NotBlank
	@Size(min = 1, max = 15, message = "Min length is 1 and max is 15")
	private String productName;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	 @Size(min = 1, max = 100, message = "Min length is 1 and max is 100")
	private String description;
	 
	 @PositiveOrZero(message = "Price must be greater than or equal to 0")
	private double price;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, orderItems, price, productId, productName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(orderItems, other.orderItems)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(productId, other.productId) && Objects.equals(productName, other.productName);
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", orderItems=" + orderItems
				+ ", description=" + description + ", price=" + price + "]";
	}

	public Product(String productId, String productName, List<OrderItem> orderItems, String description, double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.orderItems = orderItems;
		this.description = description;
		this.price = price;
	}

	public Product() {
		super();
	}

}
