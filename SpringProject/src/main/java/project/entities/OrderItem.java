package project.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Order_Items")
public class OrderItem {

	@EmbeddedId
	private OrderItemCompositePk orderItemKey;

	public OrderItem() {

		this.orderItemKey = new OrderItemCompositePk();
	}

	@NotNull(message = "Quantity is required")
	@Positive(message = "Quantity must be a positive integer")
	private int qty;

	@NotNull(message = "Price is required")
	@PositiveOrZero(message = "Price must be a positive number or zero")
	private Double price;

	public OrderItem(OrderItemCompositePk orderItemKey, int qty, Double price, Order order, Product product) {
		super();
		this.orderItemKey = orderItemKey;
		this.qty = qty;
		this.price = price;
		this.order = order;
		this.product = product;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) // default is EAGER
	@JoinColumn(name = "Order_Id", insertable = false, updatable = false)
	private Order order;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) // default is EAGER
	@JoinColumn(name = "Product_Id", insertable = false, updatable = false)
	private Product product;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public OrderItemCompositePk getOrderItemKey() {
		return orderItemKey;
	}

	public void setOrderItemKey(OrderItemCompositePk orderItemKey) {
		this.orderItemKey = orderItemKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, orderItemKey, price, product, qty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(order, other.order) && Objects.equals(orderItemKey, other.orderItemKey)
				&& Objects.equals(price, other.price) && Objects.equals(product, other.product) && qty == other.qty;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemKey=" + orderItemKey + ", qty=" + qty + ", price=" + price + ", order=" + order
				+ ", product=" + product + "]";
	}

}