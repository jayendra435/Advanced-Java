package project.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Customer_Id")
	private int customerId;

	@Column(name = "Customer_Name")
	@NotBlank(message = "Customer Name cannot be null , Give some value ")
	@Size(min = 1, max = 15, message = "Min length is 1 and max is 15")
	private String customerName;

	@Email
	@Column(unique = true, length = 20)
	@Size(max = 20, message = "Max length is 20 ")
	private String email;

	@Pattern(regexp = "[1-9][0-9]{9}")
	@Size(min = 10, max = 10)
	@NotNull
	@Positive(message = "mobile number cannot be negative")
	private String mobile;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Order> orders = new ArrayList<Order>();

	public Customer(int customerId, String customerName, String email, String mobile, List<Order> orders) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.mobile = mobile;
		this.orders = orders;
	}

	public Customer() {
		super();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, customerName, email, mobile, orders);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return customerId == other.customerId && Objects.equals(customerName, other.customerName)
				&& Objects.equals(email, other.email) && Objects.equals(mobile, other.mobile)
				&& Objects.equals(orders, other.orders);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
				+ ", mobile=" + mobile + ", orders=" + orders + "]";
	}

}
