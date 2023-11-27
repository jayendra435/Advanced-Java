package project.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "Orders")
@Entity
public class Order {

	@Id
	@Column(name = "Order_Id")
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}\\d{2}$", message = "Order ID must consist of three uppercase letters followed by two digits")
	private String orderId;

	@Column(name = "OrderDate")
	@NotBlank
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in yyyy-MM-dd format")
	private String orderDate;

	@Column(name = "Customer_Id")

	private int custId;

	@Pattern(regexp = "[NDC]", message = "Status must be in {'N','D','C'}")
	@NotBlank
	@Size(min = 1, max = 1)
	private String status;

	@Column(name = "Delivery_Date")

	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in yyyy-MM-dd format")

	private String deliveryDate;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) // default is EAGER
	@JoinColumn(name = "Customer_Id", insertable = false, updatable = false)
	private Customer customer;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(custId, customer, deliveryDate, orderDate, orderId, orderItems, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return custId == other.custId && Objects.equals(customer, other.customer)
				&& Objects.equals(deliveryDate, other.deliveryDate) && Objects.equals(orderDate, other.orderDate)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(orderItems, other.orderItems)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", custId=" + custId + ", status=" + status
				+ ", deliveryDate=" + deliveryDate + ", customer=" + customer + ", orderItems=" + orderItems + "]";
	}

	public Order(String orderId, String orderDate, int custId,
			@Pattern(regexp = "[NDC]", message = "Status must be in {'N','D','C'}") @NotBlank @Size(min = 1, max = 1) String status,
			String deliveryDate, Customer customer, List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.custId = custId;
		this.status = status;
		this.deliveryDate = deliveryDate;
		this.customer = customer;
		this.orderItems = orderItems;
	}

	public Order() {
		super();
	}

}